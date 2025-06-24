package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.git.Presentation.UI.Git;
import com.example.demo.docker.Presentation.UI.DockerServicesTable;
import com.example.demo.ide.Common.Contracts.FileIndexContract;
import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Presentation.Editor.Views.EditorView;
import com.example.demo.database.postgresql.Presentation.UI.Connections;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditorViewModel extends EditorView {
    @Autowired
    private FileIndexContract fileIndex;

    private FixedList<Tab> tabs = new FixedList<>(5);

    private File currentFile;

    private Project project;

    public void project(Project project) {
        this.project = project;

//        java.io.File root = new java.io.File(this.project.path());
//        this.indexingProject(root);

//        this.fileIndex.search("rou");
    }

    private void indexingProject(java.io.File root) {
        for (java.io.File file: root.listFiles()) {
            if (file.isFile()) {
                this.fileIndex.add(file.getPath());
            } else {
                this.indexingProject(file);
            }
        }
    }

    @FXML
    public void initialize() {
        this.initProjectDiscover(this.project);

        VBox git = (VBox) this.context.getBean(Git.class).load();
        this.gitPanel.getChildren().add(git);
        this.switchGitVisible();
        this.gitPanel.managedProperty().bind(this.gitPanel.visibleProperty());

        VBox connections = (VBox) this.context.getBean(Connections.class).load();
        this.db.setContent(connections);
        this.switchDbVisible();
        this.dbPanel.managedProperty().bind(this.dbPanel.visibleProperty());

        GridPane tableGrid = (GridPane) this.context.getBean(DockerServicesTable.class).load();
        this.docker.setContent(tableGrid);
        this.switchDockerVisible();
        this.dockerPanel.managedProperty().bind(this.dockerPanel.visibleProperty());
    }

    public void updateFile() {
        if(!this.codeEditor.getText().isEmpty()) {
            this.currentFile.changeContent(this.codeEditor.getText());
            this.updateEditor(this.currentFile.content());
        }
    }

    protected void openFile(String filePath) {
        this.currentFile = new File(filePath);

        this.updateEditor(this.currentFile.content());
        this.codeEditor.moveTo(0);

        Tab tab = new Tab(this.currentFile);
        if(!this.tabs.contains(tab)) {
            this.tabs.add(tab);

            this.updateTabs(this.tabs, tab);
        } else {
            this.chooseTab(filePath);
        }
    }

    protected void chooseTab(String filePath) {
        for(Tab tab: this.tabs.getItems()) {
            if(tab.toString().equals(filePath)) {
                this.currentFile = new File(filePath);

                this.updateEditor(this.currentFile.content());
                this.updateTabs(this.tabs, tab);
            }
        }
    }

    protected void closeTab(String filePath) {
        System.out.println("close");
        for(var i = 0; i < this.tabs.count(); i++) {
            Tab tab = this.tabs.getItems().get(i);

            if(tab.toString() == filePath) {
                this.tabs.remove(tab);

                if(i > 0) {
                    this.chooseTab(this.tabs.getItems().get(i - 1).toString());
                } else if (this.tabs.count() > 0) {
                    this.chooseTab(this.tabs.getItems().get(i).toString());
                } else {
                    this.updateEditor("");
                    this.updateTabs(this.tabs, null);
                }
            }
        }
    }
}
