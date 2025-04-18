package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.docker.commands.*;
import com.example.demo.ide.Common.Contracts.FileIndexContract;
import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Presentation.Editor.Views.EditorView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EditorViewModel extends EditorView {
    @Autowired
    private FileIndexContract fileIndex;

    private FixedList<Tab> tabs = new FixedList<>(5);

    private File currentFile;

    private Project project;

    public void project(Project project) {
        this.project = project;

        java.io.File root = new java.io.File(this.project.path());
        this.indexingProject(root);

        this.fileIndex.search("rou");
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
    }

    public void updateFile() {
        if(!this.codeEditor.getText().isEmpty()) {
            this.currentFile.changeContent(this.codeEditor.getText());
            this.updateEditor(this.currentFile);
        }
    }

    protected void openFile(String filePath) {
        this.currentFile = new File(filePath);
        this.updateEditor(this.currentFile);

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
                this.updateEditor(this.currentFile);
                this.updateTabs(this.tabs, tab);
            }
        }
    }

    @FXML
    protected void docker_up() {
        UpAllServices services = new UpAllServices();
        try {
            services.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_down() {
        StopAllServices stop = new StopAllServices();
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_up_app() {
        UpService stop = new UpService("app");
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_stop_app() {
        StopService stop = new StopService("app");
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_status() {
        GetStatuses statuses = new GetStatuses();
        try {
            statuses.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
