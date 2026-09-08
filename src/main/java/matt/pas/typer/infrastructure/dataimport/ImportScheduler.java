package matt.pas.typer.infrastructure.dataimport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImportScheduler {

    private final DataImportService dataImportService;

    public ImportScheduler(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @Async
    @Scheduled(cron = "0 30 1 * * *")
    @Scheduled(cron = "0 0 6 * * *")
    @Scheduled(cron = "0 0 16 * * *")
    public void autoImport() {

        log.info("Rozpoczęto cykliczny import danych");
        dataImportService.getAll();
    }
}


