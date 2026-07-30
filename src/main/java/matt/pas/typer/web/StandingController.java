package matt.pas.typer.web;

import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.standing.StandingService;
import matt.pas.typer.domain.standing.dto.StandingToTableDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tabele")
public class StandingController {

    private final StandingService standingService;

    public StandingController(StandingService standingService) {
        this.standingService = standingService;
    }

    @GetMapping
    String standing(Model model, @RequestParam long competitionId, @RequestParam(required = false) LocalDate startDate) {

        List<StandingToTableDto> standings;

        if (startDate == null) {
            standings = standingService.findStandingsForCurrentSeason(competitionId);
        } else {
            standings = standingService.findStandingsForSeasonStartDate(competitionId, startDate);
        }

        final String competitionName = standings.isEmpty() ? null : standings.getFirst().getCompetition().getName();
        final String competitionDates = standings.getFirst().getSeason().getStartDate() + " - " + standings.getFirst().getSeason().getEndDate();

        final List<SeasonDto> allSeasons = standingService.findAllStandingSeasonsForCompetition(competitionId);

        model.addAttribute("standings", standings);
        model.addAttribute("competitionName", competitionName);
        model.addAttribute("competitionDates", competitionDates);
        model.addAttribute("allSeason", allSeasons);

        return "standing";
    }
}
