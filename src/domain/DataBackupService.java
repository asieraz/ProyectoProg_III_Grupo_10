package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataBackupService {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final String backupFile = "backup.log";

    public void backupAsync(String data) {
        executor.submit(() -> {
            try (FileWriter fw = new FileWriter(backupFile, true)) {
                fw.write(LocalDateTime.now() + " - " + data + System.lineSeparator());
                System.out.println("Backup realizado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al escribir el backup: " + e.getMessage());
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        System.out.println("Servicio de backup finalizado.");
    }
}
