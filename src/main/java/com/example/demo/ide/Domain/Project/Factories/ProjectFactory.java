package com.example.demo.ide.Domain.Project.Factories;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import com.example.demo.ide.Domain.Project.Entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ProjectFactory {
    @Autowired
    private ComposerJsonStubContract composerJsonStub;

    public Project empty(String name, String path, boolean useComposer) {
        File dir = new File(path + "/" + name);
        dir.mkdir();

        Project project = new Project(dir);

        if (useComposer) {
            project.addComposer(this.composerJsonStub);
        }

        return project;
    }
}
