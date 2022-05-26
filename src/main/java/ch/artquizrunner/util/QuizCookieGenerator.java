package ch.artquizrunner.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuizCookieGenerator {

    @Value("${jwt.expiration.time}")
    private Integer expirationInSeconds;

    private final String QUIZ_COOKIE_NAME = "quizCookie";

    public Cookie generateQuizCookie(String jwt) {
        Cookie quizCookie = new Cookie(QUIZ_COOKIE_NAME, jwt);
        quizCookie.setPath("/");
        quizCookie.setHttpOnly(true);
        quizCookie.setMaxAge(expirationInSeconds);
        return quizCookie;
    }

    public String getQuizCookieContent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (QUIZ_COOKIE_NAME.equals(cookies[i].getName())) {
                    jwt = cookies[i].getValue();
                }
            }
            return jwt;
        }
        return null;
    }

}
