package com.example.demo.ide.Application.Project.Actions;

import com.example.demo.ide.Domain.Project.Entities.ProjectDirectory;
import com.example.demo.ide.Infrastructure.Repositories.Project.ProjectDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecentProject {
    @Autowired
    private ProjectDirectoryRepository repository;

    public ProjectDirectory execute(String path) {
        ProjectDirectory projectDirectory = this.repository.findByPath(path);

        Integer maxOrder = this.repository.findMaxOrder();

        if(maxOrder > projectDirectory.id) {
            projectDirectory.setId(maxOrder + 1);
            this.repository.save(projectDirectory);
        }

        return projectDirectory;
    }
}
