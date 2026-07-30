package matt.pas.typer.infrastructure.dataimport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataImportService {

    private final MatchApiClient matchApiClient;

    public DataImportService(MatchApiClient matchApiClient) {
        this.matchApiClient = matchApiClient;
    }

    public void getAll() {

        try {
            log.info("Start pełnego importu danych");

            matchApiClient.getAllCompetitions();
            matchApiClient.getAllFinishedMatches();
            matchApiClient.getAllStandings();

            log.info("Koniec pełnego importu danych");

        } catch (Exception e) {
            log.error("Import danych NIEUDANY", e);
        }

    }

}
