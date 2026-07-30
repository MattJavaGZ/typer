package matt.pas.typer.domain.team.dto;

import java.util.Objects;

public class TeamDto {

    private Long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getCrest() {
        return crest;
    }

    public void setCrest(String crest) {
        this.crest = crest;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(id, teamDto.id) && Objects.equals(name, teamDto.name) &&
                Objects.equals(shortName, teamDto.shortName) && Objects.equals(tla, teamDto.tla) && Objects.equals(crest, teamDto.crest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, tla, crest);
    }
}
