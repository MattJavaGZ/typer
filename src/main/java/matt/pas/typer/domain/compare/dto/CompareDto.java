package matt.pas.typer.domain.compare.dto;

import matt.pas.typer.domain.team.dto.TeamDto;


public class CompareDto {

    private TeamDto teamA;
    private TeamDto teamB;

    private LastMatchesStatDto lastMatchesStatTeamA;
    private LastMatchesStatDto lastMatchesStatTeamB;

    private HeadToHeadDto headToHead;


    public TeamDto getTeamA() {
        return teamA;
    }

    public void setTeamA(TeamDto teamA) {
        this.teamA = teamA;
    }

    public TeamDto getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamDto teamB) {
        this.teamB = teamB;
    }

    public LastMatchesStatDto getLastMatchesStatTeamA() {
        return lastMatchesStatTeamA;
    }

    public void setLastMatchesStatTeamA(LastMatchesStatDto lastMatchesStatTeamA) {
        this.lastMatchesStatTeamA = lastMatchesStatTeamA;
    }

    public LastMatchesStatDto getLastMatchesStatTeamB() {
        return lastMatchesStatTeamB;
    }

    public void setLastMatchesStatTeamB(LastMatchesStatDto lastMatchesStatTeamB) {
        this.lastMatchesStatTeamB = lastMatchesStatTeamB;
    }

    public HeadToHeadDto getHeadToHead() {
        return headToHead;
    }

    public void setHeadToHead(HeadToHeadDto headToHead) {
        this.headToHead = headToHead;
    }

    @Override
    public String toString() {
        return "CompareDto{" +
                "teamA=" + teamA +
                ", teamB=" + teamB +
                ", lastMatchesStatTeamA=" + lastMatchesStatTeamA +
                ", lastMatchesStatTeamB=" + lastMatchesStatTeamB +
                ", headToHead=" + headToHead +
                '}';
    }
}
