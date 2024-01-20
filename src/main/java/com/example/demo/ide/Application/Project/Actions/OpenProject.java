package com.example.demo.ide.Application.Project.Actions;

import com.example.demo.ide.Domain.Project.Entities.ProjectDirectory;
import com.example.demo.ide.Infrastructure.Repositories.Project.ProjectDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenProject {
    @Autowired
    private ProjectDirectoryRepository repository;

    public ProjectDirectory execute(String projectPath) {
        ProjectDirectory projectDirectory = this.repository.findByPath(projectPath);

        if (projectDirectory == null) {
            return this.createNew(projectPath);
        } else {
            return this.actualyOrder(projectDirectory);
        }
    }

    private ProjectDirectory createNew(String projectPath) {
        Integer maxOrder = this.repository.findMaxOrder();

        ProjectDirectory projectDirectory = new ProjectDirectory();
        projectDirectory.setId(maxOrder == null ? 0 : maxOrder + 1);
        projectDirectory.setPath(projectPath);

        this.repository.save(projectDirectory);

        return projectDirectory;
    }

    private ProjectDirectory actualyOrder(ProjectDirectory projectDirectory) {
        Integer maxOrder = this.repository.findMaxOrder();

        if (maxOrder > projectDirectory.id) {
            projectDirectory.setId(maxOrder + 1);
            this.repository.save(projectDirectory);
        }

        return projectDirectory;
    }
}
