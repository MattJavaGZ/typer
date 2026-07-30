package matt.pas.typer.domain.competition;

import matt.pas.typer.domain.competition.dto.CompetitionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionImportService {

    private final CompetitionRepository competitionRepository;

    public CompetitionImportService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public void saveCompetition(CompetitionDto competitionDto) {
        competitionRepository.findById(competitionDto.getId())
                .orElseGet(() -> {
                    final Competition competition = CompetitionMapper.mapToCompetition(competitionDto);
                    return competitionRepository.save(competition);
                });
    }

    public List<String> findAllCompetitionCodes() {
        return competitionRepository.findAll().stream()
                .map(Competition::getCode)
                .toList();
    }
}
