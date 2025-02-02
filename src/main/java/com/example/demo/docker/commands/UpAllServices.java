package com.example.demo.docker.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpAllServices extends Command{
    public String run() throws IOException {
        return this.execute("docker compose up -d");
    }
}
