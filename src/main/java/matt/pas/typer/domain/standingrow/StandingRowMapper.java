package matt.pas.typer.domain.standingrow;

import matt.pas.typer.domain.standingrow.dto.StandingRowDto;
import matt.pas.typer.domain.team.TeamMapper;

public class StandingRowMapper {

    public static StandingRow mapDtoToStandingRow(StandingRowDto standingRowDto) {
        final StandingRow standingRow = new StandingRow();
        standingRow.setPosition(standingRowDto.getPosition());
        standingRow.setPlayedGames(standingRowDto.getPlayedGames());
        standingRow.setWon(standingRowDto.getWon());
        standingRow.setDraw(standingRowDto.getDraw());
        standingRow.setLost(standingRowDto.getLost());
        standingRow.setPoints(standingRowDto.getPoints());
        standingRow.setGoalsFor(standingRowDto.getGoalsFor());
        standingRow.setGoalsAgainst(standingRowDto.getGoalsAgainst());
        standingRow.setGoalDifference(standingRowDto.getGoalDifference());
        return standingRow;
    }

    public static StandingRowDto mapStandingRowToDto(StandingRow standingRow) {
        final StandingRowDto standingRowDto = new StandingRowDto();
        standingRowDto.setPosition(standingRow.getPosition());
        standingRowDto.setTeam(TeamMapper.mapToTeamDto(standingRow.getTeam()));
        standingRowDto.setPlayedGames(standingRow.getPlayedGames());
        standingRowDto.setWon(standingRow.getWon());
        standingRowDto.setDraw(standingRow.getDraw());
        standingRowDto.setLost(standingRow.getLost());
        standingRowDto.setPoints(standingRow.getPoints());
        standingRowDto.setGoalsFor(standingRow.getGoalsFor());
        standingRowDto.setGoalsAgainst(standingRow.getGoalsAgainst());
        standingRowDto.setGoalDifference(standingRow.getGoalDifference());
        return standingRowDto;
    }
}
