package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Files.TokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    private FixedList<Tab> tabs = new FixedList<>(5);

    private File openedFile;

    @FXML
    public void initialize() {
        this.chooseFile(Paths.get("").toAbsolutePath() + "/project/example.php");
//        this.chooseFile(Paths.get("").toAbsolutePath() + "\\project\\short-example.php");
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
