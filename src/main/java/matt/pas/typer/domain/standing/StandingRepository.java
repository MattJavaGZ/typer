package matt.pas.typer.domain.standing;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StandingRepository extends ListCrudRepository<Standing, Long> {

    List<Standing> findAllByCompetitionId(Long competitionId);
}
