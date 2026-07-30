package matt.pas.typer.domain.match.dto;

import matt.pas.typer.domain.competition.dto.CompetitionDto;
import matt.pas.typer.domain.score.dto.ScoreDto;
import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.team.dto.TeamDto;

import java.time.LocalDateTime;

public class MatchDto {
    private Long id;
    private LocalDateTime utcDate;
    private String status;
    private Integer matchday; //któa kolejka w sezonie
    private String stage; //runda
    private LocalDateTime lastUpdated;
    private CompetitionDto competition;
    private SeasonDto season;

    private TeamDto homeTeam;
    private TeamDto awayTeam;
    private ScoreDto score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(LocalDateTime utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public CompetitionDto getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDto competition) {
        this.competition = competition;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

    public TeamDto getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamDto homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDto getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamDto awayTeam) {
        this.awayTeam = awayTeam;
    }

    public ScoreDto getScore() {
        return score;
    }

    public void setScore(ScoreDto score) {
        this.score = score;
    }
}
