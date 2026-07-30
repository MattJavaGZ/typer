package matt.pas.typer.domain.standing;

import matt.pas.typer.domain.competition.CompetitionMapper;
import matt.pas.typer.domain.season.SeasonMapper;
import matt.pas.typer.domain.standing.dto.StandingDto;
import matt.pas.typer.domain.standing.dto.StandingToTableDto;
import matt.pas.typer.domain.standingrow.StandingRowMapper;
import matt.pas.typer.domain.standingrow.dto.StandingRowDto;

import java.util.Comparator;

public class StandingMapper {

    public static StandingToTableDto mapToStandingToTableDto (Standing standing) {
        final StandingToTableDto dto = new StandingToTableDto();
        dto.setType(standing.getType());
        dto.setGroup(standing.getGroup());
        dto.setStandingRows(standing.getStandingRows().stream()
                .map(StandingRowMapper::mapStandingRowToDto)
                .sorted(Comparator.comparing(StandingRowDto::getPosition))
                .toList());
        dto.setCompetition(CompetitionMapper.mapToStandingDto(standing.getCompetition()));
        dto.setSeason(SeasonMapper.mapToDto(standing.getSeason()));
        return dto;
    }
}
