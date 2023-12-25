package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Files.TokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Components.Editor.Directory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;

@Component
public class EditorController extends Controller {
    @Autowired
    private ContentStylist stylist;

    @FXML
    private HBox tabPanel;

    @FXML
    private StyleClassedTextArea codeEditor;

    @FXML
    private SplitPane window;

    @FXML
    private VBox projectDiscover;

    private FixedList<Tab> tabs = new FixedList<>(5);

    private File openedFile;

    private Project project;

    public void project(Project project) {
        this.project = project;
    }

    @FXML
    public void initialize() {
        this.chooseFile(Paths.get("").toAbsolutePath() + "/project/example.php");
//        this.chooseFile(Paths.get("").toAbsolutePath() + "\\project\\short-example.php");
//        System.out.println(this.project.files());

        for(java.io.File file: this.project.files()) {
            HBox fileComponent;

            if (file.isDirectory()) {
                fileComponent = (HBox) this.context.getBean(Directory.class).load();
                Label name = (Label) fileComponent.lookup("#dirName");
                name.setText(file.getName());
            } else {
                fileComponent = (HBox) this.context.getBean(com.example.demo.ide.UI.Components.Editor.File.class).load();
                Label name = (Label) fileComponent.lookup("#fileName");
                name.setText(file.getName());
            }

            this.projectDiscover.getChildren().add(fileComponent);
        }
    }

    public void changedContent() {
        this.openedFile.changeContent(this.codeEditor.getText());
        this.updateStyledContentOpenedFile();
    }

    private void updateStyledContentOpenedFile() {
        TokenizedFile tokenizedFile = new TokenizedFile(this.openedFile);
        StyledTokenizedFile styledTokenizedFile = new StyledTokenizedFile(tokenizedFile);

        int caretCurrentPosition = this.codeEditor.getCaretPosition();

        this.stylist.styling(
            this.codeEditor,
            styledTokenizedFile
        );

        this.codeEditor.moveTo(caretCurrentPosition);
    }

    public void openFile(MouseEvent mouseEvent) {
        this.chooseFile(Paths.get("").toAbsolutePath() + "/project/short-example.php");
    }

    public void chooseFile(String path) {
        this.openedFile = new File(path);
        this.updateStyledContentOpenedFile();

        Tab tab = new Tab(this.openedFile);
        if(!this.tabs.contains(tab)) {
            this.tabs.add(tab);

            VBox tabComponent = (VBox) this.context.getBean(com.example.demo.ide.UI.Components.Editor.Tab.class).load();
            Label property = (Label) tabComponent.lookup("#title");
            property.setText(this.openedFile.name());

            this.tabPanel.getChildren().add(tabComponent);
        }
    }
}
