package matt.pas.typer.domain.wrapper;

import matt.pas.typer.domain.competition.dto.CompetitionDto;
import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.standing.dto.StandingDto;

import java.util.List;

public class LeagueStandingsResponseDto {

    private CompetitionDto competition;
    private SeasonDto season;
    private List<StandingDto> standings;

    public CompetitionDto getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDto competition) {
        this.competition = competition;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

    public List<StandingDto> getStandings() {
        return standings;
    }

    public void setStandings(List<StandingDto> standings) {
        this.standings = standings;
    }
}
