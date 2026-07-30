package matt.pas.typer.domain.score.dto;

public class TimeScoreDto {
    private Integer home;
    private Integer away;

    public TimeScoreDto(Integer home, Integer away) {
        this.home = home;
        this.away = away;
    }

    public TimeScoreDto() {
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public Integer getAway() {
        return away;
    }

    public void setAway(Integer away) {
        this.away = away;
    }
}
