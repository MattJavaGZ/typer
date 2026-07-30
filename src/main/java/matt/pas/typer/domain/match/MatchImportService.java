package matt.pas.typer.domain.match;

import jakarta.transaction.Transactional;
import matt.pas.typer.domain.competition.Competition;
import matt.pas.typer.domain.competition.CompetitionMapper;
import matt.pas.typer.domain.competition.CompetitionRepository;
import matt.pas.typer.domain.competition.dto.CompetitionDto;
import matt.pas.typer.domain.match.dto.MatchDto;
import matt.pas.typer.domain.season.Season;
import matt.pas.typer.domain.season.SeasonMapper;
import matt.pas.typer.domain.season.SeasonRepository;
import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.team.Team;
import matt.pas.typer.domain.team.TeamMapper;
import matt.pas.typer.domain.team.TeamRepository;
import matt.pas.typer.domain.team.dto.TeamDto;
import org.springframework.stereotype.Service;

@Service
public class MatchImportService {

    private final TeamRepository teamRepository;
    private final CompetitionRepository competitionRepository;
    private final SeasonRepository seasonRepository;
    private final MatchRepository matchRepository;


    public MatchImportService(TeamRepository teamRepository, CompetitionRepository competitionRepository, SeasonRepository seasonRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.competitionRepository = competitionRepository;
        this.seasonRepository = seasonRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void saveMatch(MatchDto dto) {

        Match match = MatchMapper.mapToMatch(dto);

        match.setCompetition(findOrCreateCompetition(dto.getCompetition()));
        match.setSeason(findOrCreateSeason(dto.getSeason()));
        match.setHomeTeam(findOrCreateTeam(dto.getHomeTeam()));
        match.setAwayTeam(findOrCreateTeam(dto.getAwayTeam()));

        matchRepository.save(match);
    }

    public Team findOrCreateTeam(TeamDto dto) {
        return teamRepository.findById(dto.getId())
                .orElseGet(() -> {
                    final Team team = TeamMapper.mapToTeam(dto);
                    return teamRepository.save(team);
                });
    }

    public Competition findOrCreateCompetition(CompetitionDto competitionDto) {
        return competitionRepository.findById(competitionDto.getId())
                .orElseGet(() -> {
                    final Competition competition = CompetitionMapper.mapToCompetition(competitionDto);
                    return competitionRepository.save(competition);
                });
    }

    public Season findOrCreateSeason(SeasonDto seasonDto) {
        return seasonRepository.findById(seasonDto.getId())
                .orElseGet(() -> {
                    final Season season = SeasonMapper.mapToSeason(seasonDto);
                    return seasonRepository.save(season);
                });
    }
}
