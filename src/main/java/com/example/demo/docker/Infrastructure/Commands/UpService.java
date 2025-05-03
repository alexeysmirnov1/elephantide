package com.example.demo.docker.Infrastructure.Commands;

import com.example.demo.docker.Domain.Entities.Service;

import java.io.IOException;
import java.util.ArrayList;

public class UpService extends Command {
    private String service;

    public UpService(String service) {
        this.service = service;
    }

    public ArrayList<Service> run() throws IOException {
        return this.execute("docker compose up -d " + this.service);
    }
}
