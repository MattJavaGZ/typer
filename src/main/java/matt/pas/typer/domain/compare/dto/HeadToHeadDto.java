package matt.pas.typer.domain.compare.dto;

import matt.pas.typer.domain.match.dto.MatchDto;

import java.util.List;

public class HeadToHeadDto {

    private List<MatchDto> lastH2HMatches;

    private int teamAWin;
    private int draw;
    private int teamBWin;

    private int teamAGoalsScored;
    private int teamBGoalsScored;


    public List<MatchDto> getLastH2HMatches() {
        return lastH2HMatches;
    }

    public void setLastH2HMatches(List<MatchDto> lastH2HMatches) {
        this.lastH2HMatches = lastH2HMatches;
    }

    public int getTeamAWin() {
        return teamAWin;
    }

    public void setTeamAWin(int teamAWin) {
        this.teamAWin = teamAWin;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getTeamBWin() {
        return teamBWin;
    }

    public void setTeamBWin(int teamBWin) {
        this.teamBWin = teamBWin;
    }

    public int getTeamAGoalsScored() {
        return teamAGoalsScored;
    }

    public void setTeamAGoalsScored(int teamAGoalsScored) {
        this.teamAGoalsScored = teamAGoalsScored;
    }

    public int getTeamBGoalsScored() {
        return teamBGoalsScored;
    }

    public void setTeamBGoalsScored(int teamBGoalsScored) {
        this.teamBGoalsScored = teamBGoalsScored;
    }


}
