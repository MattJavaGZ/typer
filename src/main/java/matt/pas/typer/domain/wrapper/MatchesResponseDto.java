package matt.pas.typer.domain.wrapper;

import matt.pas.typer.domain.match.dto.MatchDto;

import java.util.List;

public class MatchesResponseDto {

    private Integer count;
    private List<MatchDto> matches;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<MatchDto> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDto> matches) {
        this.matches = matches;
    }
}
