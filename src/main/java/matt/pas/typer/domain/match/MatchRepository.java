package matt.pas.typer.domain.match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface MatchRepository extends ListCrudRepository<Match, Long> {

    List<Match> findAllByHomeTeam_IdOrAwayTeam_IdOrderByUtcDateDesc(Long homeTeamId, Long awayTeamId, Pageable pageable);

    @Query("""
    SELECT m
    FROM Match m
    WHERE (m.homeTeam.id = :teamAId AND m.awayTeam.id = :teamBId)
       OR (m.homeTeam.id = :teamBId AND m.awayTeam.id = :teamAId)
    ORDER BY m.utcDate DESC
    """)
    List<Match> findLastH2HMatches(@Param("teamAId") Long teamAId, @Param("teamBId") Long teamBId, Pageable pageable);

    int countByHomeTeam_IdOrAwayTeam_Id(Long homeTeamId, Long awayTeamId);
}




