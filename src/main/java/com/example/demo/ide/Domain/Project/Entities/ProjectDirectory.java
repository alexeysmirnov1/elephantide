package com.example.demo.ide.Domain.Project.Entities;

import javax.persistence.*;

@Entity
public class ProjectDirectory {
    @Id
    public int id;

    public String path;

    public ProjectDirectory() {}

    public String getPath() {
        return this.path;
    }

    public int getId() {
        return this.id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId(int order) {
        this.id = order;
    }

    public String getName() {
        String[] splited = this.path.split("\\\\");
        return splited[splited.length - 1];
    }
}
