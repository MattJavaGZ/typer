package matt.pas.typer.domain.score.dto;

public class ScoreDto {
    private String winner;
    private String duration;

    private TimeScoreDto fullTime;
    private TimeScoreDto halfTime;
    private TimeScoreDto regularTime;
    private TimeScoreDto extraTime;
    private TimeScoreDto penalties;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public TimeScoreDto getFullTime() {
        return fullTime;
    }

    public void setFullTime(TimeScoreDto fullTime) {
        this.fullTime = fullTime;
    }

    public TimeScoreDto getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(TimeScoreDto halfTime) {
        this.halfTime = halfTime;
    }

    public TimeScoreDto getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(TimeScoreDto regularTime) {
        this.regularTime = regularTime;
    }

    public TimeScoreDto getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(TimeScoreDto extraTime) {
        this.extraTime = extraTime;
    }

    public TimeScoreDto getPenalties() {
        return penalties;
    }

    public void setPenalties(TimeScoreDto penalties) {
        this.penalties = penalties;
    }
}
