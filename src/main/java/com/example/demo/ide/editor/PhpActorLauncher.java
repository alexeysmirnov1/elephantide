package com.example.demo.ide.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class PhpActorLauncher {
    private Process phpActorProcess;

    public void start() throws IOException {
//        System.out.println(PhpActorLauncher.class.getClassLoader().getResource("/external/modules/php/bin/8_1/win/php.exe"));
        ProcessBuilder pb = new ProcessBuilder(
            ".\\src\\main\\resources\\external\\modules\\php\\bin\\8_1\\win\\php",
            ".\\src\\main\\resources\\external\\modules\\php\\phpactor.phar",
            "language-server",
            "--address=127.0.0.1:9999"
        );

        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        phpActorProcess = pb.start();

        // Запуск потока для чтения вывода
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(phpActorProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[PHP Actor] " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Проверка статуса запуска
        if (!phpActorProcess.isAlive()) {
            throw new IllegalStateException("Process failed to start");
        }
    }

    public Process getProcess() {
        return phpActorProcess;
    }

    public void stop() {
        if (phpActorProcess != null) {
            phpActorProcess.destroy();
        }
    }
}
