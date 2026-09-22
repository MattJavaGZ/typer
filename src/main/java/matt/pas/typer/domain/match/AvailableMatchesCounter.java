package matt.pas.typer.domain.match;

import org.springframework.stereotype.Component;

@Component
public class AvailableMatchesCounter {

    public final static int MAX_MATCHES_STATS_SELECT = 10;
    public final static int DEFAULT_MATCHES_STATS_SELECT = 5;

    private final MatchRepository matchRepository;

    public AvailableMatchesCounter(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public int countMatchesByTeamIdWithLimit (long teamId, int limit) {
        final int matches = matchRepository.countByHomeTeam_IdOrAwayTeam_Id(teamId, teamId);
        return Math.min(matches, limit);
    }

}
