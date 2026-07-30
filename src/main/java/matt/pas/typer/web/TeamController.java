package matt.pas.typer.web;

import matt.pas.typer.domain.compare.CompareService;
import matt.pas.typer.domain.compare.dto.LastMatchesStatDto;
import matt.pas.typer.domain.team.TeamService;
import matt.pas.typer.domain.team.dto.TeamDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@RequestMapping("/druzyny")
public class TeamController {

    private final TeamService teamService;
    private final CompareService compareService;

    public TeamController(TeamService teamService, CompareService compareService) {
        this.teamService = teamService;
        this.compareService = compareService;
    }

    @GetMapping("/szukaj")
    @ResponseBody
    public List<TeamDto> findTeamsByUserSearch(String text) {
        return teamService.findTeamsByUserSearch(text);
    }

    @GetMapping("/staty")
    String getTeamStats(@RequestParam Long teamStatsId, Model model, @RequestParam(required = false) Integer matchesToStats) {

        final TeamDto teamDto = teamService.getTeamById(teamStatsId);
        final LastMatchesStatDto lastMatchesStat = compareService.getLastMatchesStat(teamStatsId, matchesToStats);

        model.addAttribute("team", teamDto);
        model.addAttribute("lastMatchesStat", lastMatchesStat);
        model.addAttribute("matchesToStats", matchesToStats);

        return "team-stats";
    }
}
