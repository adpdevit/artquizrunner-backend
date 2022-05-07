package ch.artquizrunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.artquizrunner.configuration.GameParameters;
import ch.artquizrunner.model.GameState;

@Service
public class GameEngineService {

    @Autowired
    private GameParameters gameParameters;

    public GameState getInitialGameState() {
        final GameState gameState = new GameState();

        gameState.setBadInARow(0L);
        gameState.setGoodInARow(0L);
        gameState.setCurrentPoints(0L);
        gameState.setEndState(gameParameters.getInitialEndState());

        return gameState;
    }

    public GameState evolveGameStatePositive(final GameState curentGameState) {
        return evolveGameState(curentGameState, gameParameters.getEvolPoints(),
                gameParameters.getEvolEndStatePositive(), curentGameState.getGoodInARow() + 1L, 0L);
    }

    public GameState evolveGameStateNegative(final GameState curentGameState) {
        return evolveGameState(curentGameState, 0L, gameParameters.getEvolEndStateNegative(), 0L,
                curentGameState.getBadInARow() + 1L);
    }

    private GameState evolveGameState(final GameState currentGameState, final Long evolPoints, final Long evolEndState,
            final Long goodInARow, final Long badInARow) {
        Long finalScoreAdjustment = evolPoints;
        if (goodInARow != 0 && goodInARow % gameParameters.getEvolEndStatePositiveInARow() == 0) {
            finalScoreAdjustment += gameParameters.getEvolPoints() / 2;
        } else if (badInARow != 0 && badInARow % gameParameters.getEvolEndStateNegativeInARow() == 0) {
            finalScoreAdjustment -= gameParameters.getEvolPoints() / 4;
        }

        final GameState nextGameState = new GameState();
        nextGameState.setBadInARow(badInARow);
        nextGameState.setGoodInARow(goodInARow);
        nextGameState.setCurrentPoints(currentGameState.getCurrentPoints() + finalScoreAdjustment);
        nextGameState.setEndState(currentGameState.getEndState() + evolEndState);

        return nextGameState;
    }
}
