package matt.pas.typer.domain.wrapper;

import matt.pas.typer.domain.competition.dto.CompetitionDto;

import java.util.List;

public class CompetitionsResponseDto {

    private List<CompetitionDto> competitions;

    public List<CompetitionDto> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionDto> competitions) {
        this.competitions = competitions;
    }
}
