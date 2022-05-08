package ch.artquizrunner.util;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.artquizrunner.model.QuizState;

@Component
public class JWTProcessor {

    private final String JWT_QUIZSTATE_CLAIM = "quizState";
    private final String JWT_ISSUER = "artquizrunner";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.time}")
    private Integer expirationInSeconds;

    @Autowired
    private ObjectMapper mapper;

    public String generateAndSignQuizStateToken(QuizState quizState)
            throws JsonProcessingException, IllegalArgumentException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, expirationInSeconds);
            return JWT.create().withIssuer(JWT_ISSUER).withExpiresAt(calendar.getTime())
                    .withClaim(JWT_QUIZSTATE_CLAIM, mapper.writeValueAsString(quizState)).sign(algorithm);
        } catch (JWTCreationException exception) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Cannot generate JWT", exception);
        }
        return null;
    }

    public QuizState verifyAndGetQuizStateFromToken(String jwt) throws JsonMappingException, JsonProcessingException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret); // use more secure key
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build(); // Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(jwt);
            String serializedQuizState = decodedJWT.getClaim(JWT_QUIZSTATE_CLAIM).asString();
            if (serializedQuizState != null) {
                return mapper.readValue(serializedQuizState, QuizState.class);
            }
        } catch (JWTVerificationException exception) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Given JWT is not valid", exception);
        }
        return null;
    }
}
