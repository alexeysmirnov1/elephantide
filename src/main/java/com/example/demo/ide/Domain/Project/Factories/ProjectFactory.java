package com.example.demo.ide.Domain.Project.Factories;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import com.example.demo.ide.Domain.Project.Entities.Project;
import com.example.demo.ide.Infrastructure.Stubs.ComposerJson;
import com.example.demo.ide.Infrastructure.Stubs.ComposerPackageJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import java.io.File;
import java.io.IOException;

@Component
public class ProjectFactory {
    @Autowired
    private ComposerJsonStubContract composerJsonStub;

    public Project empty(String path, boolean useComposer, boolean git) {
        Project project = new Project(path);

        if (useComposer) {
            project.addComposer(new ComposerJson());
        }

        if (git) {
            project.createGit();
        }

        return project;
    }

    public Project composerPackage(String path, boolean git) {
        Project project = new Project(path);
        project.addComposer(new ComposerPackageJson());

        if(git) {
            project.createGit();
        }

        return project;
    }

    public Project laravel(String path, String version, boolean git) {
        Project project = new Project(path);

        this.copyLaravelStub(path, version);

        if (git) {
            project.createGit();
        }

        return project;
    }

    private void copyLaravelStub(String path, String version) {
        File stub = new File(this.getClass().getClassLoader().getResource("stubs/laravel/10").getPath());

        try {
            FileSystemUtils.copyRecursively(
                stub,
                new File(path)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
