package matt.pas.typer.domain.match;

import matt.pas.typer.domain.competition.CompetitionMapper;
import matt.pas.typer.domain.match.dto.MatchDto;
import matt.pas.typer.domain.score.ScoreMapper;
import matt.pas.typer.domain.season.SeasonMapper;
import matt.pas.typer.domain.team.TeamMapper;


public class MatchMapper {

    public static Match mapToMatch(MatchDto matchDto) {
        final Match match = new Match();
        match.setId(matchDto.getId());
        match.setUtcDate(matchDto.getUtcDate());
        match.setStatus(Match.Status.valueOf(matchDto.getStatus()));
        match.setMatchday(matchDto.getMatchday());
        match.setStage(matchDto.getStage());
        match.setLastUpdated(matchDto.getLastUpdated());

        match.setScore(ScoreMapper.mapDtoToScore(matchDto.getScore()));

        return match;
    }

    public static MatchDto mapToDto(Match match) {
        final MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setUtcDate(match.getUtcDate());
        matchDto.setStatus(match.getStatus().name());
        matchDto.setMatchday(match.getMatchday());
        matchDto.setStage(match.getStage());
        matchDto.setLastUpdated(match.getLastUpdated());
        matchDto.setCompetition(CompetitionMapper.mapToDto(match.getCompetition()));
        matchDto.setSeason(SeasonMapper.mapToDto(match.getSeason()));
        matchDto.setHomeTeam(TeamMapper.mapToTeamDto(match.getHomeTeam()));
        matchDto.setAwayTeam(TeamMapper.mapToTeamDto(match.getAwayTeam()));
        matchDto.setScore(ScoreMapper.mapToDto(match.getScore()));
        return matchDto;
    }



}
