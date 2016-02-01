package squares;

public class Scoring {
    private final String team;
    private final Score score;

    public Scoring(String team, Score score) {

        this.team = team;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scoring scoring = (Scoring) o;

        if (team != null ? !team.equals(scoring.team) : scoring.team != null) return false;
        return score == scoring.score;

    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Scoring{" +
                "team='" + team + '\'' +
                ", score=" + score +
                '}';
    }
}
