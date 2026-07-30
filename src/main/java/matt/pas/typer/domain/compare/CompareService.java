package matt.pas.typer.domain.compare;

import matt.pas.typer.domain.compare.dto.CompareDto;
import matt.pas.typer.domain.compare.dto.HeadToHeadDto;
import matt.pas.typer.domain.compare.dto.LastMatchesStatDto;
import matt.pas.typer.domain.match.MatchMapper;
import matt.pas.typer.domain.match.MatchRepository;
import matt.pas.typer.domain.match.dto.MatchDto;
import matt.pas.typer.domain.score.Score;
import matt.pas.typer.domain.score.dto.ScoreDto;
import matt.pas.typer.domain.score.dto.TimeScoreDto;
import matt.pas.typer.domain.team.TeamMapper;
import matt.pas.typer.domain.team.TeamRepository;
import matt.pas.typer.domain.team.dto.TeamDto;
import matt.pas.typer.domain.team.exceptions.TeamNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompareService {

    public final static Pageable LAST_10 = PageRequest.of(0, 10);
    final static Pageable LAST_5 = PageRequest.of(0, 5);

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public CompareService(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }


//    public CompareDto getTeamsCompare(List<Long> teamIds) {
//
//        final CompareDto compare = new CompareDto();
//
//        final List<TeamDto> teams = teamRepository.findAllById(teamIds).stream()
//                .map(TeamMapper::mapToTeamDto)
//                .toList();
//
//        compare.setTeamA(teams.get(0));
//        compare.setTeamB(teams.get(1));
//
//        final LastMatchesStatDto lastMatchesStatTeamA = getLastMatchesStat(compare.getTeamA().getId(), LAST_10);
//        final LastMatchesStatDto lastMatchesStatTeamB = getLastMatchesStat(compare.getTeamB().getId(), LAST_10);
//
//        compare.setLastMatchesStatTeamA(lastMatchesStatTeamA);
//        compare.setLastMatchesStatTeamB(lastMatchesStatTeamB);
//
//        final HeadToHeadDto headToHead = getHeadToHead(compare.getTeamA(), compare.getTeamB());
//        compare.setHeadToHead(headToHead);
//
//        return compare;
//    }
//
////    private Pageable getPageable(int matches) {
////        return  PageRequest.of(0, matches);
////    }
//
//    public LastMatchesStatDto getLastMatchesStat(Long teamId, Pageable pageable) {
//        final LastMatchesStatDto teamStats = new LastMatchesStatDto();
//
//        final TeamDto team = teamRepository.findById(teamId).map(TeamMapper::mapToTeamDto).orElseThrow(
//                () -> new TeamNotFoundException("Brak wybranej drużyny"));
//
//        final List<MatchDto> lastMaches = matchRepository.findAllByHomeTeam_IdOrAwayTeam_IdOrderByUtcDateDesc(teamId, teamId, pageable)
//                .stream()
//                .map(MatchMapper::mapToDto)
//                .toList();
//
//        teamStats.setLastMatchesTeam(lastMaches);
//
//        getAndSetTeamResults(team, lastMaches, teamStats);
//
//        teamStats.setGoalsScored(getTeamGolasScored(team, lastMaches));
//
//        teamStats.setGoalsConceded(getTeamGolasConceded(team, lastMaches));
//
//        teamStats.setGoalsScoredAverage((double) teamStats.getGoalsScored() / lastMaches.size());
//
//        teamStats.setGoalsConcededAverage((double) teamStats.getGoalsConceded() / lastMaches.size());
//
//        teamStats.setGoalsAverage((double) (teamStats.getGoalsScored() + teamStats.getGoalsConceded()) / lastMaches.size() );
//
//        teamStats.setLastResults(getLastResults(team, lastMaches));
//
//        teamStats.setBtts(getBtts(lastMaches));
//
//        teamStats.setNoGoalsConceded(getNoGoalsConceded(team, lastMaches));
//
//        return teamStats;
//    }

public CompareDto getTeamsCompare(List<Long> teamIds, Integer matchesToStatsA, Integer matchesToStatsB) {

    final CompareDto compare = new CompareDto();

    final List<TeamDto> teams = teamRepository.findAllById(teamIds).stream()
            .map(TeamMapper::mapToTeamDto)
            .toList();

    compare.setTeamA(teams.get(0));
    compare.setTeamB(teams.get(1));

    final LastMatchesStatDto lastMatchesStatTeamA = getLastMatchesStat(compare.getTeamA().getId(),matchesToStatsA);
    final LastMatchesStatDto lastMatchesStatTeamB = getLastMatchesStat(compare.getTeamB().getId(), matchesToStatsB);

    compare.setLastMatchesStatTeamA(lastMatchesStatTeamA);
    compare.setLastMatchesStatTeamB(lastMatchesStatTeamB);

    final HeadToHeadDto headToHead = getHeadToHead(compare.getTeamA(), compare.getTeamB());
    compare.setHeadToHead(headToHead);

    return compare;
}

//    private Pageable getPageable(int matches) {
//        return  PageRequest.of(0, matches);
//    }

    public LastMatchesStatDto getLastMatchesStat(Long teamId, Integer matchesToStats) {
        final LastMatchesStatDto teamStats = new LastMatchesStatDto();

        final TeamDto team = teamRepository.findById(teamId).map(TeamMapper::mapToTeamDto).orElseThrow(
                () -> new TeamNotFoundException("Brak wybranej drużyny"));

        final List<MatchDto> lastMaches = matchRepository.findAllByHomeTeam_IdOrAwayTeam_IdOrderByUtcDateDesc(teamId, teamId, LAST_10)
                .stream()
                .map(MatchMapper::mapToDto)
                .toList();

        final List<MatchDto> lastMatchesToStats;
        if (matchesToStats == null || lastMaches.size() < matchesToStats) lastMatchesToStats = lastMaches;
        else lastMatchesToStats = lastMaches.subList(0, matchesToStats);

        teamStats.setLastMatchesTeam(lastMaches);

        getAndSetTeamResults(team, lastMatchesToStats, teamStats);

        teamStats.setGoalsScored(getTeamGolasScored(team, lastMatchesToStats));

        teamStats.setGoalsConceded(getTeamGolasConceded(team, lastMatchesToStats));

        teamStats.setGoalsScoredAverage((double) teamStats.getGoalsScored() / lastMatchesToStats.size());

        teamStats.setGoalsConcededAverage((double) teamStats.getGoalsConceded() / lastMatchesToStats.size());

        teamStats.setGoalsAverage((double) (teamStats.getGoalsScored() + teamStats.getGoalsConceded()) / lastMatchesToStats.size() );

        teamStats.setLastResults(getLastResults(team, lastMaches));

        teamStats.setBtts(getBtts(lastMatchesToStats));

        teamStats.setNoGoalsConceded(getNoGoalsConceded(team, lastMatchesToStats));

        return teamStats;
    }

    private List<Results> getLastResults(TeamDto team, List<MatchDto> lastMaches) {
        List<Results> lastResultsList = new ArrayList<>();

        lastMaches.stream()
                .limit(5)
                .forEach(match -> lastResultsList.add(matchResultVeryfi(team, match)));

        return lastResultsList;
    }

    private HeadToHeadDto getHeadToHead(TeamDto teamA, TeamDto teamB) {
        final HeadToHeadDto h2H = new HeadToHeadDto();
        final List<MatchDto> h2HMatches = matchRepository.findLastH2HMatches(teamA.getId(), teamB.getId(), LAST_5).stream()
                .map(MatchMapper::mapToDto).toList();
        h2H.setLastH2HMatches(h2HMatches);

        getAndSetH2HResultsStat(teamA, teamB, h2HMatches, h2H);

        return h2H;
    }

    private void getAndSetH2HResultsStat(TeamDto teamA, TeamDto teamB, List<MatchDto> lastMaches, HeadToHeadDto h2H) {

        int draw = 0;
        int teamAWin = 0;
        int teamBWin = 0;

        for (MatchDto match : lastMaches) {
            if (match.getScore().getWinner().equals(Score.Winner.DRAW.toString())) draw++;
            if (winnerMatchesFilter(teamA, match)) teamAWin++;
            if (winnerMatchesFilter(teamB, match)) teamBWin++;
        }
        h2H.setDraw(draw);
        h2H.setTeamAWin(teamAWin);
        h2H.setTeamBWin(teamBWin);

        h2H.setTeamAGoalsScored(getTeamGolasScored(teamA, lastMaches));
        h2H.setTeamBGoalsScored(getTeamGolasScored(teamB, lastMaches));
    }

    private void getAndSetTeamResults(TeamDto team, List<MatchDto> lastMaches, LastMatchesStatDto teamStats) {

        int draw = 0;
        int win = 0;
        int lost = 0;

        for (MatchDto match : lastMaches) {
            if (match.getScore().getWinner().equals(Score.Winner.DRAW.toString())) draw++;
            if (winnerMatchesFilter(team, match)) win++;
            if (lostMatchesFilter(team, match)) lost++;
        }
        teamStats.setDraw(draw);
        teamStats.setWin(win);
        teamStats.setLost(lost);
    }

    private int getTeamGolasScored(TeamDto team, List<MatchDto> lastMaches) {

        final Integer homeGolasScored = lastMaches.stream()
                .filter(match -> match.getHomeTeam().getId().equals(team.getId()))
                .map(this::getVeryfiedHomeScore)
                .reduce(0, Integer::sum);

        final Integer awayGolasScored = lastMaches.stream()
                .filter(match -> match.getAwayTeam().getId().equals(team.getId()))
                .map(this::getVeryfiedAwayScore)
                .reduce(0, Integer::sum);

        return homeGolasScored + awayGolasScored;
    }

    private int getTeamGolasConceded(TeamDto team, List<MatchDto> lastMaches) {

        final Integer homeGolasConceded = lastMaches.stream()
                .filter(match -> match.getHomeTeam().getId().equals(team.getId()))
                .map(this::getVeryfiedAwayScore)
                .reduce(0, Integer::sum);

        final Integer awayGolasConceded = lastMaches.stream()
                .filter(match -> match.getAwayTeam().getId().equals(team.getId()))
                .map(this::getVeryfiedHomeScore)
                .reduce(0, Integer::sum);

        return homeGolasConceded + awayGolasConceded;
    }

    private int getVeryfiedHomeScore(MatchDto match) {
        final ScoreDto score = match.getScore();
        if (score.getRegularTime().getHome() == null) return score.getFullTime().getHome();
        if (score.getExtraTime().getHome() != null) return score.getExtraTime().getHome() + score.getRegularTime().getHome();
        return score.getRegularTime().getHome();
    }

    private int getVeryfiedAwayScore(MatchDto match) {
        final ScoreDto score = match.getScore();
        if (score.getRegularTime().getHome() == null) return score.getFullTime().getAway();
        if (score.getExtraTime().getHome() != null) return score.getExtraTime().getAway() + score.getRegularTime().getAway();
        return score.getRegularTime().getAway();
    }


    private int getBtts(List<MatchDto> lastMaches) {
        return (int) lastMaches.stream()
                .filter(match -> match.getScore().getFullTime().getHome() > 0 &&
                        match.getScore().getFullTime().getAway() > 0)
                .count();
    }

    private int getNoGoalsConceded(TeamDto team, List<MatchDto> lastMaches) {
        return (int) lastMaches.stream()
                .filter(match ->
                        (match.getAwayTeam().getId().equals(team.getId()) && match.getScore().getFullTime().getHome() == 0)
                                || (match.getHomeTeam().getId().equals(team.getId()) && match.getScore().getFullTime().getAway() == 0))
                .count();
    }


    private boolean winnerMatchesFilter(TeamDto team, MatchDto match) {
        return (match.getHomeTeam().getId().equals(team.getId()) && match.getScore().getWinner().equals(Score.Winner.HOME_TEAM.toString())) ||
                (match.getAwayTeam().getId().equals(team.getId()) && match.getScore().getWinner().equals(Score.Winner.AWAY_TEAM.toString()));
    }

    private boolean lostMatchesFilter(TeamDto team, MatchDto match) {
        return (match.getHomeTeam().getId().equals(team.getId()) && match.getScore().getWinner().equals(Score.Winner.AWAY_TEAM.toString())) ||
                (match.getAwayTeam().getId().equals(team.getId()) && match.getScore().getWinner().equals(Score.Winner.HOME_TEAM.toString()));
    }


    public Results matchResultVeryfi(TeamDto team, MatchDto match) {
        if (match.getScore().getFullTime().getHome().equals(match.getScore().getFullTime().getAway()))
            return Results.DRAW;
        else if (matchWinVeryfi(team, match)) return Results.WIN;
        else return Results.LOSS;
    }

    private boolean matchWinVeryfi(TeamDto team, MatchDto match) {
        return (match.getScore().getWinner().equals(Score.Winner.HOME_TEAM.toString()) &&
                match.getHomeTeam().equals(team)) ||
                (match.getScore().getWinner().equals(Score.Winner.AWAY_TEAM.toString()) &&
                        match.getAwayTeam().equals(team));
    }

}
