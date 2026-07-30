package matt.pas.typer.domain.team;

import matt.pas.typer.domain.team.dto.TeamDto;
import matt.pas.typer.domain.team.exceptions.TeamNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> findTeamsByUserSearch (String userText) {
        return teamRepository.finaAllTeamsToSearch(userText)
                .stream().map(TeamMapper::mapToTeamDto)
                .toList();
    }

    public TeamDto getTeamById(Long id) {
        return teamRepository.findById(id)
                .map(TeamMapper::mapToTeamDto)
                .orElseThrow(() -> new TeamNotFoundException("Brak wybranej drużyny w bazie"));
    }

}
