package com.example.demo.docker;

import java.util.ArrayList;

public class DockerEngine {
    private ArrayList<DockerService> services;

    public DockerEngine() {
        this.services = new ArrayList<>();
    }

    public void upServices() {
        this.services.forEach(service -> service.up());
    }

    public void downServices() {
        this.services.forEach(service -> service.stop());
    }
}
