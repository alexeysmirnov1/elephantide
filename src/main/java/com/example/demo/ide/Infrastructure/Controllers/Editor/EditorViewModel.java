package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Presentation.Editor.Views.EditorView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

@Component
public class EditorViewModel extends EditorView {
    private FixedList<Tab> tabs = new FixedList<>(5);

    private File currentFile;

    private Project project;

    public void project(Project project) {
        this.project = project;
    }

    @FXML
    public void initialize() {
//        this.chooseFile(new File(Paths.get("").toAbsolutePath() + "/project/example.php"));
//        this.chooseFile(Paths.get("").toAbsolutePath() + "\\project\\short-example.php");

        this.initProjectDiscover(this.project);
    }

    public void updateFile() {
        this.currentFile.changeContent(this.codeEditor.getText());
        this.updateEditor(this.currentFile);
    }

    public void openFile(String filePath) {
        this.currentFile = new File(filePath);
        this.updateEditor(this.currentFile);

        Tab tab = new Tab(this.currentFile);
        if(!this.tabs.contains(tab)) {
            this.tabs.add(tab);

            this.updateTabs(this.tabs);
        }
    }

    //кнопка + на панели табов
    public void showProject(MouseEvent mouseEvent) {

    }
}
