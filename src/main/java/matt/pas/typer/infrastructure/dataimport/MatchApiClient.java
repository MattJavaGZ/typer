package matt.pas.typer.infrastructure.dataimport;

import lombok.extern.slf4j.Slf4j;
import matt.pas.typer.domain.competition.CompetitionImportService;
import matt.pas.typer.domain.match.MatchImportService;
import matt.pas.typer.domain.standing.StandingImportService;
import matt.pas.typer.domain.wrapper.CompetitionsResponseDto;
import matt.pas.typer.domain.wrapper.LeagueStandingsResponseDto;
import matt.pas.typer.domain.wrapper.MatchesResponseDto;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class MatchApiClient {

    @Value("${app.api.key}")
    private String apiKey;
    @Value("${app.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final MatchImportService matchImportService;
    private final StandingImportService standingImportService;
    private final CompetitionImportService competitionImportService;

    public MatchApiClient(RestTemplate restTemplate, MatchImportService matchImportService, StandingImportService standingImportService,
                          CompetitionImportService competitionImportService) {
        this.restTemplate = restTemplate;
        this.matchImportService = matchImportService;
        this.standingImportService = standingImportService;
        this.competitionImportService = competitionImportService;
    }

    public void getAllFinishedMatches() {
        final String initialUrl = apiUrl + "competitions/";
        final String lastUrl = "/matches?status=FINISHED";
        String url;

        final List<String> competitionCodes = competitionImportService.findAllCompetitionCodes();
        for (String code : competitionCodes) {

            url = initialUrl + code + lastUrl;

            MatchesResponseDto body = getApiResponse(url, MatchesResponseDto.class);

            if (body == null || body.getMatches() == null) {
                log.warn("Brak danych dla ligi: " + code);
                continue;
            }

            body.getMatches().forEach(matchImportService::saveMatch);
            final int matchCount = body.getMatches().size();
            log.info("Zapisano {} meczów dla ligi {}", matchCount, code);

            rateLimit();
        }
    }


    public void getAllStandings() {
        final String initialUrl = apiUrl + "competitions/";
        final String lastUrl = "/standings";
        String url;

        final List<String> competitionCodes = competitionImportService.findAllCompetitionCodes();
        for (String code : competitionCodes) {

            url = initialUrl + code + lastUrl;

            LeagueStandingsResponseDto body = getApiResponse(url, LeagueStandingsResponseDto.class);

            if (body == null || body.getStandings() == null) {
                log.warn("Brak danych tabeli dla ligi: " + code);
                continue;
            }

            standingImportService.saveStanding(body);
            log.info("Zapisano tabelę ligi {}", code);

            rateLimit();
        }
    }

    public void getAllCompetitions() {
        String url = apiUrl + "competitions/";

        CompetitionsResponseDto body = getApiResponse(url, CompetitionsResponseDto.class);

        if (body == null || body.getCompetitions() == null || body.getCompetitions().isEmpty()) {
            log.warn("Brak danych przy pobieraniu listy lig");
            return;
        }

        body.getCompetitions().forEach(competitionImportService::saveCompetition);
        log.info("Zapisano {} lig", body.getCompetitions().size());

    }

    private <T> T getApiResponse(String url, Class<T> responseType) {
        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    getHttpEntity(),
                    responseType
            );
            return response.getBody();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.warn("Błąd API [{}]: {}", url, e.getStatusCode());
        } catch (ResourceAccessException e) {
            log.warn("Timeout API [{}]", url);
        }
        return null;
    }

    private @NonNull HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);
        return new HttpEntity<>(headers);
    }

    private void rateLimit() {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.info("Rate limit przerwany (shutdown)");
        }
    }

}
