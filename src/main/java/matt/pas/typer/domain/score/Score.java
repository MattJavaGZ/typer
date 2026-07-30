package matt.pas.typer.domain.score;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "winner", length = 50)
    private Winner winner;
    @Enumerated(EnumType.STRING)
    @Column(name = "duration", length = 50)
    private Duration duration;

    private Integer fullHome;
    private Integer fullAway;

    private Integer halfHome;
    private Integer halfAway;

    private Integer regularHome;
    private Integer regularAway;

    private Integer extraHome;
    private Integer extraAway;

    private Integer penaltiesHome;
    private Integer penaltiesAway;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getFullHome() {
        return fullHome;
    }

    public void setFullHome(Integer fullHome) {
        this.fullHome = fullHome;
    }

    public Integer getFullAway() {
        return fullAway;
    }

    public void setFullAway(Integer fullAway) {
        this.fullAway = fullAway;
    }

    public Integer getHalfHome() {
        return halfHome;
    }

    public void setHalfHome(Integer halfHome) {
        this.halfHome = halfHome;
    }

    public Integer getHalfAway() {
        return halfAway;
    }

    public void setHalfAway(Integer halfAway) {
        this.halfAway = halfAway;
    }

    public Integer getRegularHome() {
        return regularHome;
    }

    public void setRegularHome(Integer regularHome) {
        this.regularHome = regularHome;
    }

    public Integer getRegularAway() {
        return regularAway;
    }

    public void setRegularAway(Integer regularAway) {
        this.regularAway = regularAway;
    }

    public Integer getExtraHome() {
        return extraHome;
    }

    public void setExtraHome(Integer extraHome) {
        this.extraHome = extraHome;
    }

    public Integer getExtraAway() {
        return extraAway;
    }

    public void setExtraAway(Integer extraAway) {
        this.extraAway = extraAway;
    }

    public Integer getPenaltiesHome() {
        return penaltiesHome;
    }

    public void setPenaltiesHome(Integer penaltiesHome) {
        this.penaltiesHome = penaltiesHome;
    }

    public Integer getPenaltiesAway() {
        return penaltiesAway;
    }

    public void setPenaltiesAway(Integer penaltiesAway) {
        this.penaltiesAway = penaltiesAway;
    }

    public enum Winner {
        HOME_TEAM, AWAY_TEAM, DRAW, UNKNOWN;

        public static Winner fromApi(String value) {
            if (value == null) {
                return UNKNOWN;
            }
            return Winner.valueOf(value);
        }
    }

    public enum Duration {
        REGULAR, EXTRA_TIME, PENALTY_SHOOTOUT, UNKNOWN;

        public static Duration fromApi(String value) {
            if (value == null) {
                return UNKNOWN;
            }
            return Duration.valueOf(value);
        }
    }
}
