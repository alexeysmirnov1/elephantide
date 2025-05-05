package com.example.demo.docker.Domain.Entities;

public class Service {
    public final String name;
    public final String image;
    public final String status;
    public final String ports;

    public Service(String name, String image, String status, String ports) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.ports = ports;
    }
}
