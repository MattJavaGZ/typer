package matt.pas.typer.domain.match;

import jakarta.persistence.*;
import matt.pas.typer.domain.competition.Competition;
import matt.pas.typer.domain.score.Score;
import matt.pas.typer.domain.season.Season;
import matt.pas.typer.domain.team.Team;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    private Long id;
    @Column(name = "utc_datetime")
    private LocalDateTime utcDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "match_status", length = 50, nullable = false)
    private Status status;
    private Integer matchday;
    private String stage;
    private LocalDateTime lastUpdated;
    @ManyToOne
    @JoinColumn(name="competition_id")
    private Competition competition;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne
    @JoinColumn(name="home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name="away_team_id")
    private Team awayTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id")
    private Score score;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    enum Status {
        SCHEDULED, LIVE, IN_PLAY, PAUSED, FINISHED, POSTPONED, SUSPENDED, CANCELLED;
    }
}
