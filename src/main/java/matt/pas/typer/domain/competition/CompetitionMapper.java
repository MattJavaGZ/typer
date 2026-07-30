package matt.pas.typer.domain.competition;

import matt.pas.typer.domain.competition.dto.CompetitionDto;
import matt.pas.typer.domain.competition.dto.CompetitionStandingDto;

public class CompetitionMapper {

    public static Competition mapToCompetition(CompetitionDto competitionDto) {
        final Competition competition = new Competition();
        competition.setId(competitionDto.getId());
        competition.setName(competitionDto.getName());
        competition.setCode(competitionDto.getCode());
        competition.setType(competitionDto.getType());
        competition.setEmblem(competitionDto.getEmblem());
        return competition;
    }

    public static CompetitionDto mapToDto(Competition competition) {
        final CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setId(competition.getId());
        competitionDto.setName(competition.getName());
        competitionDto.setCode(competition.getCode());
        competitionDto.setType(competition.getType());
        competitionDto.setEmblem(competition.getEmblem());
        if (competition.getWorkingName() != null) {
            competitionDto.setWorkingName(competition.getWorkingName());
        }
        return competitionDto;
    }

    public static CompetitionStandingDto mapToStandingDto(Competition competition) {
        final CompetitionStandingDto competitionStandingDto = new CompetitionStandingDto();
        competitionStandingDto.setId(competition.getId());
        competitionStandingDto.setName(competition.getName());
        competitionStandingDto.setCode(competition.getCode());
        competitionStandingDto.setEmblem(competition.getEmblem());
        if (competition.getWorkingName() != null) {
            competitionStandingDto.setWorkingName(competition.getWorkingName());
        }
        return competitionStandingDto;
    }
}
