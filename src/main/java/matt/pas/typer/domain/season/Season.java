package matt.pas.typer.domain.season;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "seasons")
public class Season {

    @Id
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentMatchday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id, season.id) && Objects.equals(startDate, season.startDate) && Objects.equals(endDate, season.endDate) && Objects.equals(currentMatchday, season.currentMatchday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, currentMatchday);
    }
}
