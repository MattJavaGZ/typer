package matt.pas.typer.domain.standing;

import matt.pas.typer.domain.season.Season;
import matt.pas.typer.domain.season.SeasonMapper;
import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.standing.dto.StandingToTableDto;
import matt.pas.typer.domain.standingrow.dto.StandingRowDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StandingService {

    private final StandingRepository standingRepository;

    public StandingService(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }

    public List<StandingToTableDto> findStandingsForCurrentSeason(long competitionId) {

        List<Standing> latestSeasonStandings =
                standingRepository.findAllByCompetitionId(competitionId).stream()
                        .filter(s -> s.getSeason() != null && s.getSeason().getStartDate() != null)
                        .collect(Collectors.groupingBy(
                                s -> s.getSeason().getStartDate()
                        ))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByKey())
                        .orElseThrow(() -> new IllegalArgumentException("Brak tabel dla ligi"))
                        .getValue();

        return latestSeasonStandings.stream()
                .map(StandingMapper::mapToStandingToTableDto)
                .toList();
    }

    public List<StandingToTableDto> findStandingsForSeasonStartDate(long competitionId, LocalDate startDate) {
        return standingRepository.findAllByCompetitionId(competitionId).stream()
                .filter(s -> s.getSeason().getStartDate().equals(startDate))
                .map(StandingMapper::mapToStandingToTableDto)
                .toList();
    }

    public List<SeasonDto> findAllStandingSeasonsForCompetition(long competitionId) {
       return standingRepository.findAllByCompetitionId(competitionId).stream()
                .map(Standing::getSeason)
                .distinct()
                .map(SeasonMapper::mapToDto)
                .toList();
    }
}
