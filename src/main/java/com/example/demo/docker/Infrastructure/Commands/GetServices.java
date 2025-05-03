package com.example.demo.docker.Infrastructure.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetServices {
    public String run() throws IOException {
        Process process = new ProcessBuilder()
            .command("bash", "-c", "docker compose config --services")
            .directory(new File("/root/projects/mdat"))
            .redirectErrorStream(true)
            .start();

        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append("").append(line);
                System.out.println(line);
            }
        }

        return result.toString();
    }
}
