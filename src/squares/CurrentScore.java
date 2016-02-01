package squares;

/**
 * Created by Jessica on 1/31/2016.
 */
public class CurrentScore {
    private int teamA;
    private int teamB;

    public CurrentScore(int teamA, int teamB) {
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
