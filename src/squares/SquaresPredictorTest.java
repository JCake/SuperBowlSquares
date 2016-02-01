package squares;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SquaresPredictorTest {

    @Test
    public void predictsNoScoreIfPersonIsAlreadyWinning(){
        SquaresPredictor predictor = new SquaresPredictor();
        assertEquals(new HashSet<List<Scoring>>(), predictor.calculateRequiredScoring(new CurrentScore(10,14),new SquareSpot(0,4)));
    }

    @Test
    public void predictsFieldGoalForFirstTeamIfFirstTeamOffBy3(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.FIELD_GOAL)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(7, 14), new SquareSpot(0, 4)));
    }

    @Test
         public void predictsFieldGoalForSecondTeamIfSecondTeamOffBy3(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamB", Score.FIELD_GOAL)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(10, 12), new SquareSpot(0, 5)));
    }

    @Test
    public void predictsFieldGoalForBothTeamsIfBothTeamsOffBy3(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.FIELD_GOAL),new Scoring("teamB", Score.FIELD_GOAL)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(7, 12), new SquareSpot(0, 5)));
    }

    @Test
    public void predictsTouchDownWithExtraPointForTeamOffBy7(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.TOUCHDOWN_WITH_PAT),new Scoring("teamB", Score.FIELD_GOAL)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(3, 12), new SquareSpot(0, 5)));
    }

    @Test
    public void predictsTouchDownWithExtraPointForMultipleTeamsOffBy7(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.TOUCHDOWN_WITH_PAT),new Scoring("teamB", Score.TOUCHDOWN_WITH_PAT)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(3, 18), new SquareSpot(0, 5)));
    }

    @Test
    public void predictsSafetiesForTeasmOffBy2(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.SAFETY),new Scoring("teamB", Score.SAFETY)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(10, 18), new SquareSpot(2, 0)));
    }

    @Test
    public void offBy6CanBeTwoFieldGoalsOrTDWithNoExtraPoint(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamB", Score.FIELD_GOAL),new Scoring("teamB", Score.FIELD_GOAL)));
        expectedScoring.add(Arrays.asList(new Scoring("teamB", Score.TOUCHDOWN_WITH_NO_PAT)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(10, 14), new SquareSpot(0, 0)));
    }

    @Test
    public void offBy6ForBothTeamsProducesAllPossibleCombos(){
        SquaresPredictor predictor = new SquaresPredictor();
        HashSet<List<Scoring>> expectedScoring = new HashSet<>();
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.FIELD_GOAL),new Scoring("teamA", Score.FIELD_GOAL),
                new Scoring("teamB", Score.FIELD_GOAL),new Scoring("teamB", Score.FIELD_GOAL)));
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.FIELD_GOAL),new Scoring("teamA", Score.FIELD_GOAL),
                new Scoring("teamB", Score.TOUCHDOWN_WITH_NO_PAT)));
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.TOUCHDOWN_WITH_NO_PAT),
                new Scoring("teamB", Score.FIELD_GOAL),new Scoring("teamB", Score.FIELD_GOAL)));
        expectedScoring.add(Arrays.asList(new Scoring("teamA", Score.TOUCHDOWN_WITH_NO_PAT),
                new Scoring("teamB", Score.TOUCHDOWN_WITH_NO_PAT)));
        assertEquals(expectedScoring, predictor.calculateRequiredScoring(new CurrentScore(10, 14), new SquareSpot(6, 0)));
    }
}
