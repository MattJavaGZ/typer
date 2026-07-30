package matt.pas.typer.domain.competition;

import matt.pas.typer.domain.competition.dto.CompetitionStandingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<CompetitionStandingDto> findAllCompetitions() {
       return competitionRepository.findAll().stream()
                .map(CompetitionMapper::mapToStandingDto)
                .toList();
    }
}
