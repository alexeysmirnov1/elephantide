package com.example.demo.docker.Domain.Entities;

import java.util.ArrayList;

public class Service {
    public final String name;
    public final String image;
    public final String status;
    public final ArrayList<String> ports;

    public Service(String name, String image, String status, ArrayList<String> ports) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.ports = ports;
    }
}
