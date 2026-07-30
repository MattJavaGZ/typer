package matt.pas.typer.web;

import matt.pas.typer.domain.competition.CompetitionService;
import matt.pas.typer.domain.competition.dto.CompetitionStandingDto;
import matt.pas.typer.infrastructure.dataimport.DataImportService;
import matt.pas.typer.infrastructure.dataimport.MatchApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CompetitionService competitionService;
    private final DataImportService dataImportService;

    public HomeController(CompetitionService competitionService, DataImportService dataImportService) {
        this.competitionService = competitionService;
        this.dataImportService = dataImportService;
    }

    @GetMapping("/")
    public String index(Model model) {
        final List<CompetitionStandingDto> competitions = competitionService.findAllCompetitions();
        model.addAttribute("competitions", competitions);
//        dataImportService.getAll();
        return "index";
    }
}

