package matt.pas.typer.web;

import matt.pas.typer.domain.competition.CompetitionService;
import matt.pas.typer.domain.competition.dto.CompetitionStandingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CompetitionService competitionService;

    public HomeController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }


    @GetMapping("/")
    public String index(Model model) {
        final List<CompetitionStandingDto> competitions = competitionService.findAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "index";
    }
}

