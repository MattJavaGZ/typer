package matt.pas.typer.domain.standing;

import jakarta.persistence.*;
import matt.pas.typer.domain.competition.Competition;
import matt.pas.typer.domain.season.Season;
import matt.pas.typer.domain.standingrow.StandingRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "competition_id",
                        "season_id",
                        "standing_type",
                        "standing_group"
                }
        )
)
public class Standing {

    @Id
    @Column(name = "standing_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;
    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;
    @Column(name = "standing_type")
    private String type;
    @Column(name = "standing_group")
    private String group;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "standing_id")
    private List<StandingRow> standingRows = new ArrayList<>();


    public void computeId() {
        this.id = generateId(
                competition.getId(),
                season.getId(),
                type,
                group
        );
    }

    private static Long generateId(Long competitionId, Long seasonId, String type, String group) {
        return (long) Objects.hash(
                competitionId,
                seasonId,
                type,
                normalize(group)
        ) & 0x7fffffffL;
    }

    private static String normalize(String value) {
        return value == null ? "__NULL__" : value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

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

    public List<StandingRow> getStandingRows() {
        return standingRows;
    }

    public void setStandingRows(List<StandingRow> standingRows) {
        this.standingRows = standingRows;
    }
}
