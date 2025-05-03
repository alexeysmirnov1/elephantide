package com.example.demo.docker.Infrastructure.Commands;

import com.example.demo.docker.Domain.Entities.Service;

import java.io.IOException;
import java.util.ArrayList;

public class GetStatuses extends Command {
    public ArrayList<Service> run() throws IOException {
        return this.execute("docker compose ps --format json");
    }
}
