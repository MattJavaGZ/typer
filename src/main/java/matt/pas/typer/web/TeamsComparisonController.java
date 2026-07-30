package matt.pas.typer.web;

import matt.pas.typer.domain.compare.CompareService;
import matt.pas.typer.domain.compare.dto.CompareDto;
import matt.pas.typer.domain.team.dto.TeamDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@RequestMapping("/porownanie-druzyn")
public class TeamsComparisonController {

    private final CompareService compareService;

    public TeamsComparisonController(CompareService compareService) {
        this.compareService = compareService;
    }

    @GetMapping
    String compareTeams(Model model, @RequestParam List<Long> teamIds, @RequestParam(required = false) Integer matchesToStatsA,
                        @RequestParam(required = false) Integer matchesToStatsB) {

        final CompareDto teamsCompare = compareService.getTeamsCompare(teamIds, matchesToStatsA, matchesToStatsB );

        model.addAttribute("teamsCompare", teamsCompare);
        model.addAttribute("matchesToStatsA", matchesToStatsA);
        model.addAttribute("matchesToStatsB", matchesToStatsB);
        return "comparison";
    }
}
