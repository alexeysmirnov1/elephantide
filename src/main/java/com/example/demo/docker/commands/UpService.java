package com.example.demo.docker.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpService extends Command {
    private String service;

    public UpService(String service) {
        this.service = service;
    }

    public String run() throws IOException {
        return this.execute("docker compose up -d " + this.service);
    }
}
