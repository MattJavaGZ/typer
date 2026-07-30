package matt.pas.typer.infrastructure.dataimport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@Slf4j
public class StartupDataImport {

    private final DataImportService dataImportService;

    public StartupDataImport(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

            log.info("Uruchomienie aplikacji - rozpoczęto pierwszy import danych");
            dataImportService.getAll();
    }
}
