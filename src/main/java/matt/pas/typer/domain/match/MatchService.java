package matt.pas.typer.domain.match;

import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public int countMatchesByTeamIdMax10 (long teamId) {
        final int matches = matchRepository.countByHomeTeam_IdOrAwayTeam_Id(teamId, teamId);
        return Math.min(matches, 10);
    }
}
