package matt.pas.typer.domain.standing.dto;

import matt.pas.typer.domain.competition.dto.CompetitionStandingDto;
import matt.pas.typer.domain.season.dto.SeasonDto;
import matt.pas.typer.domain.standingrow.dto.StandingRowDto;

import java.util.List;

public class StandingToTableDto {

    private String type;
    private String group;
    private List<StandingRowDto> standingRows;
    private CompetitionStandingDto competition;
    private SeasonDto season;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<StandingRowDto> getStandingRows() {
        return standingRows;
    }

    public void setStandingRows(List<StandingRowDto> standingRows) {
        this.standingRows = standingRows;
    }

    public CompetitionStandingDto getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionStandingDto competition) {
        this.competition = competition;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }
}
