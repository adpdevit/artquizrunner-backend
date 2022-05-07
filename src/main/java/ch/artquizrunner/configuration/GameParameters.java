package ch.artquizrunner.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameParameters {

    @Value("${game.state.init.end-state}")
    private Long initialEndState;

    @Value("${game.state.evol.points}")
    private Long evolPoints;

    @Value("${game.state.evol.end-state.positive}")
    private Long evolEndStatePositive;

    @Value("${game.state.evol.end-state.positive.inarow}")
    private Long evolEndStatePositiveInARow;

    @Value("${game.state.evol.end-state.negative}")
    private Long evolEndStateNegative;

    @Value("${game.state.evol.end-state.negative.inarow}")
    private Long evolEndStateNegativeInARow;

    public Long getInitialEndState() {
        return initialEndState;
    }

    public Long getEvolPoints() {
        return evolPoints;
    }

    public Long getEvolEndStatePositive() {
        return evolEndStatePositive;
    }

    public Long getEvolEndStatePositiveInARow() {
        return evolEndStatePositiveInARow;
    }

    public Long getEvolEndStateNegative() {
        return evolEndStateNegative;
    }

    public Long getEvolEndStateNegativeInARow() {
        return evolEndStateNegativeInARow;
    }

}
