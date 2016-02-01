package squares;


public class SquareSpot {
    private final int teamA;
    private final int teamB;

    public SquareSpot(int teamA, int teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public int getTeamA() {
        return teamA;
    }

    public int getTeamB() {
        return teamB;
    }

}
