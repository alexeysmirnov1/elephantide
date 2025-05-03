package com.example.demo.docker;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
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
