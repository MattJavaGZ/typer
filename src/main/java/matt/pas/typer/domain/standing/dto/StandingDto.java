package matt.pas.typer.domain.standing.dto;

import matt.pas.typer.domain.standingrow.dto.StandingRowDto;

import java.util.List;

public class StandingDto {

    private String type;
    private String group;
    private List<StandingRowDto> table;

    public List<StandingRowDto> getTable() {
        return table;
    }

    public void setTable(List<StandingRowDto> table) {
        this.table = table;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
