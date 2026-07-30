package matt.pas.typer.domain.compare.dto;

import matt.pas.typer.domain.compare.Results;
import matt.pas.typer.domain.match.dto.MatchDto;

import java.util.List;

public class LastMatchesStatDto {

    private List<MatchDto> lastMatchesTeam;

    private int win;
    private int draw;
    private int lost;

    private int goalsScored;
    private int goalsConceded;

    private double goalsScoredAverage;
    private double goalsConcededAverage;
    private double goalsAverage;

    private int btts;

    private int noGoalsConceded;

    private List<Results> lastResults;

    public List<MatchDto> getLastMatchesTeam() {
        return lastMatchesTeam;
    }

    public void setLastMatchesTeam(List<MatchDto> lastMatchesTeam) {
        this.lastMatchesTeam = lastMatchesTeam;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public double getGoalsScoredAverage() {
        return goalsScoredAverage;
    }

    public void setGoalsScoredAverage(double goalsScoredAverage) {
        this.goalsScoredAverage = goalsScoredAverage;
    }

    public double getGoalsConcededAverage() {
        return goalsConcededAverage;
    }

    public void setGoalsConcededAverage(double goalsConcededAverage) {
        this.goalsConcededAverage = goalsConcededAverage;
    }

    public double getGoalsAverage() {
        return goalsAverage;
    }

    public void setGoalsAverage(double goalsAverage) {
        this.goalsAverage = goalsAverage;
    }

    public int getBtts() {
        return btts;
    }

    public void setBtts(int btts) {
        this.btts = btts;
    }

    public int getNoGoalsConceded() {
        return noGoalsConceded;
    }

    public void setNoGoalsConceded(int noGoalsConceded) {
        this.noGoalsConceded = noGoalsConceded;
    }

    public List<Results> getLastResults() {
        return lastResults;
    }

    public void setLastResults(List<Results> lastResults) {
        this.lastResults = lastResults;
    }
}
