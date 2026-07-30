package matt.pas.typer.domain.score;

import matt.pas.typer.domain.score.dto.ScoreDto;
import matt.pas.typer.domain.score.dto.TimeScoreDto;

public class ScoreMapper {

    public static Score mapDtoToScore(ScoreDto scoreDto) {

        if (scoreDto == null) {
            return null;
        }

        Score score = new Score();

        score.setWinner(Score.Winner.fromApi(scoreDto.getWinner()));
        score.setDuration(Score.Duration.fromApi(scoreDto.getDuration()));

        if (scoreDto.getFullTime() != null) {
            score.setFullHome(scoreDto.getFullTime().getHome());
            score.setFullAway(scoreDto.getFullTime().getAway());
        }

        if (scoreDto.getHalfTime() != null) {
            score.setHalfHome(scoreDto.getHalfTime().getHome());
            score.setHalfAway(scoreDto.getHalfTime().getAway());
        }

        if (scoreDto.getRegularTime() != null) {
            score.setRegularHome(scoreDto.getRegularTime().getHome());
            score.setRegularAway(scoreDto.getRegularTime().getAway());
        }

        if (scoreDto.getExtraTime() != null) {
            score.setExtraHome(scoreDto.getExtraTime().getHome());
            score.setExtraAway(scoreDto.getExtraTime().getAway());
        }

        if (scoreDto.getPenalties() != null) {
            score.setPenaltiesHome(scoreDto.getPenalties().getHome());
            score.setPenaltiesAway(scoreDto.getPenalties().getAway());
        }
        return score;
    }

    public static ScoreDto mapToDto (Score score) {
        final ScoreDto scoreDto = new ScoreDto();
        scoreDto.setWinner(score.getWinner().name());
        scoreDto.setDuration(score.getDuration().name());

        scoreDto.setFullTime(new TimeScoreDto(score.getFullHome(), score.getFullAway()));
        scoreDto.setHalfTime(new TimeScoreDto(score.getHalfHome(), score.getHalfAway()));
        scoreDto.setRegularTime(new TimeScoreDto(score.getRegularHome(), score.getRegularAway()));
        scoreDto.setExtraTime(new TimeScoreDto(score.getExtraHome(), score.getExtraAway()));
        scoreDto.setPenalties(new TimeScoreDto(score.getPenaltiesHome(), score.getPenaltiesAway()));

        return scoreDto;
    }
}
