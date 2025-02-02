package com.example.demo.docker.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

abstract public class Command {

    public Command() {}

    public abstract String run() throws IOException;

    protected String execute(String command) throws IOException {
        Process process = new ProcessBuilder()
            .command("bash", "-c", command)
            .directory(new File("/root/projects/mdat"))
            .redirectErrorStream(true)
            .start();

        StringBuilder result = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append("").append(line);
            System.out.println(line);
        }

        return result.toString();
    }
}
