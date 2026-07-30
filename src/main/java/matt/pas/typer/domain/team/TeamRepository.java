package matt.pas.typer.domain.team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TeamRepository extends ListCrudRepository<Team, Long> {

//    List<Team> findAllByNameContainsIgnoreCaseOrShortNameContainsIgnoreCaseOrTlaContainsIgnoreCase
//            (String name, String shortName, String tla);

    @Query("""
       SELECT t FROM Team t
       WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :userText, '%'))
          OR LOWER(t.shortName) LIKE LOWER(CONCAT('%', :userText, '%'))
          OR LOWER(t.tla) LIKE LOWER(CONCAT('%', :userText, '%'))
       """)
    List<Team> finaAllTeamsToSearch(String userText);
}
