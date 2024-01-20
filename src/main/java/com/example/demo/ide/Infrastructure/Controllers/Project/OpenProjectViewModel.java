package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Application.Project.Actions.OpenProject;
import com.example.demo.ide.Application.Project.Actions.RecentProject;
import com.example.demo.ide.Domain.Project.Entities.ProjectDirectory;
import com.example.demo.ide.Infrastructure.Repositories.Project.ProjectDirectoryRepository;
import com.example.demo.ide.Presentation.Editor.UI.Scenes.Editor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenProjectViewModel extends AbstractProjectController {
    @Autowired
    private ProjectDirectoryRepository repository;

    @FXML
    private VBox projects;

    @FXML
    public void initialize() {
        for (ProjectDirectory project: this.repository.findAll()) {
            VBox component = (VBox) this.context.getBean(com.example.demo.ide.Presentation.Project.UI.Components.ProjectDirectory.class).load();
            Label title = (Label) component.lookup("#title");
            title.setText(project.getName());
            Label path = (Label) component.lookup("#path");
            path.setText(project.getPath());
            component.setOnMouseClicked(event -> this.recent(project.getPath()));

            this.projects.getChildren().add(component);
        }
    }

    @Override
    protected void create() {

    }

    @FXML
    public void open() {
        OpenProject action = this.context.getBean(OpenProject.class);
        ProjectDirectory projectDirectory = action.execute(this.path.getText());

        this.stage.switchScene(this.context.getBean(Editor.class)
            .project(projectDirectory.getPath())
            .load());
    }

    @FXML
    public void recent(String path) {
        RecentProject action = this.context.getBean(RecentProject.class);
        ProjectDirectory projectDirectory = action.execute(path);

        this.stage.switchScene(this.context.getBean(Editor.class)
            .project(projectDirectory.getPath())
            .load());
    }

    @FXML
    protected void validate() {}
}
