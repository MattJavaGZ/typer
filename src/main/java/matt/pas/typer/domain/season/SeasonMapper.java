package matt.pas.typer.domain.season;

import matt.pas.typer.domain.season.dto.SeasonDto;

public class SeasonMapper {

    public static Season mapToSeason(SeasonDto seasonDto) {
        final Season season = new Season();
        season.setId(seasonDto.getId());
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        season.setCurrentMatchday(seasonDto.getCurrentMatchday());
        return season;
    }

    public static SeasonDto mapToDto(Season season) {
        final SeasonDto seasonDto = new SeasonDto();
        seasonDto.setId(season.getId());
        seasonDto.setStartDate(season.getStartDate());
        seasonDto.setEndDate(season.getEndDate());
        seasonDto.setCurrentMatchday(season.getCurrentMatchday());
        return seasonDto;
    }
}
