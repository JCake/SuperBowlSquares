package squares;

import java.util.*;

public class SquaresPredictor {
    public Set<List<Scoring>> calculateRequiredScoring(CurrentScore currentScore, SquareSpot squareSpot) {
        if(currentScore.getTeamA() % 10 == squareSpot.getTeamA() &&
                currentScore.getTeamB() % 10 == squareSpot.getTeamB()){
            return new HashSet<>();
        }

        HashSet<List<Scoring>> possibleScorings = new HashSet<>();


        List<List<Scoring>> teamAScorings = findAllowedScoringForThisTeam("teamA",currentScore.getTeamA(), squareSpot.getTeamA());
        List<List<Scoring>> teamBScorings = findAllowedScoringForThisTeam("teamB",currentScore.getTeamB(), squareSpot.getTeamB());


        for(List<Scoring> teamAScoring : teamAScorings){
            for(List<Scoring> teamBScoring: teamBScorings){
                List<Scoring> scorings = new ArrayList<>();
                scorings.addAll(teamAScoring);
                scorings.addAll(teamBScoring);
                possibleScorings.add(scorings);
            }
        }


        return possibleScorings;
    }

    //TODO add tests for off by 1, 4, 5, 8, 9
    private List<List<Scoring>> findAllowedScoringForThisTeam(String teamName, int teamsCurScore, int teamSquare) {
        List<List<Scoring>> possibleScoringForTeam = new ArrayList<>();

        if((teamsCurScore + 2) % 10 == teamSquare){
            possibleScoringForTeam.add(Arrays.asList(new Scoring(teamName,Score.SAFETY)));
        }
        else if((teamsCurScore + 3) % 10 == teamSquare){
            possibleScoringForTeam.add(Arrays.asList(new Scoring(teamName, Score.FIELD_GOAL)));
        }
        else if((teamsCurScore + 6) % 10 == teamSquare){
            possibleScoringForTeam.add(Arrays.asList(new Scoring(teamName, Score.FIELD_GOAL), new Scoring(teamName, Score.FIELD_GOAL)));
            possibleScoringForTeam.add(Arrays.asList(new Scoring(teamName, Score.TOUCHDOWN_WITH_NO_PAT)));
        }
        else if((teamsCurScore + 7) % 10 == teamSquare){
            possibleScoringForTeam.add(Arrays.asList(new Scoring(teamName,Score.TOUCHDOWN_WITH_PAT)));
        }else{
            possibleScoringForTeam.add(new ArrayList<>());
        }

        return possibleScoringForTeam;
    }
}
