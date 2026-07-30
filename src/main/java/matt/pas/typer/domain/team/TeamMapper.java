package matt.pas.typer.domain.team;

import matt.pas.typer.domain.team.dto.TeamDto;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        final TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setShortName(team.getShortName());
        teamDto.setTla(team.getTla());
        teamDto.setCrest(team.getCrest());
        return teamDto;
    }

    public static Team mapToTeam(TeamDto teamDto) {
        final Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        team.setShortName(teamDto.getShortName());
        team.setTla(teamDto.getTla());
        team.setCrest(teamDto.getCrest());
        return team;
    }
}
