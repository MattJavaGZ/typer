package matt.pas.typer.domain.standing;

import lombok.extern.slf4j.Slf4j;
import matt.pas.typer.domain.match.MatchImportService;
import matt.pas.typer.domain.standing.dto.StandingDto;
import matt.pas.typer.domain.standingrow.StandingRow;
import matt.pas.typer.domain.standingrow.StandingRowMapper;
import matt.pas.typer.domain.standingrow.StandingRowRepository;
import matt.pas.typer.domain.standingrow.dto.StandingRowDto;
import matt.pas.typer.domain.team.Team;
import matt.pas.typer.domain.wrapper.LeagueStandingsResponseDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandingImportService {

    private final StandingRepository standingRepository;
    private final MatchImportService matchImportService;

    public StandingImportService(StandingRepository standingRepository, MatchImportService matchImportService) {
        this.standingRepository = standingRepository;
        this.matchImportService = matchImportService;
    }

    public void saveStanding(LeagueStandingsResponseDto leagueStandingsResponseDto) {

        if (leagueStandingsResponseDto.getStandings().isEmpty()
                || leagueStandingsResponseDto.getStandings().getFirst().getTable().isEmpty()) {
            log.warn("Brak tabeli dla ligi {}", leagueStandingsResponseDto.getCompetition().getCode());
            return;
        }

        final StandingRowDto firstTeam = leagueStandingsResponseDto.getStandings().getFirst().getTable().getFirst();

        if (leagueStandingsResponseDto.getSeason().getCurrentMatchday() == 1 && firstTeam.getPlayedGames() > 1) {
            log.warn("Pominięto import tabeli {} - API zwróciło dane poprzedniego sezonu.", leagueStandingsResponseDto.getCompetition().getCode());
            return;
        }


        for (StandingDto standing : leagueStandingsResponseDto.getStandings()) {
            final Standing standingToSave = new Standing();

            standingToSave.setCompetition(matchImportService.findOrCreateCompetition(leagueStandingsResponseDto.getCompetition()));
            standingToSave.setSeason(matchImportService.findOrCreateSeason(leagueStandingsResponseDto.getSeason()));

            standingToSave.setType(standing.getType());

            String group = standing.getGroup();
            standingToSave.setGroup(
                    group != null && (group.startsWith("Group") || group.startsWith("League") )? group : null
            );

            standing.getTable().forEach(standingRow -> {
                final StandingRow standingRowToSave = StandingRowMapper.mapDtoToStandingRow(standingRow);
                final Team team = matchImportService.findOrCreateTeam(standingRow.getTeam());
                standingRowToSave.setTeam(team);
                standingToSave.getStandingRows().add(standingRowToSave);
            });
            standingToSave.computeId();
            standingRepository.save(standingToSave);
        }

    }
}
