package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Files.TokenizedFile;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import javafx.fxml.FXML;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;

@Component
public class EditorController extends Controller {
    @Autowired
    private ContentStylist stylist;

    @FXML
    private StyleClassedTextArea codeEditor;

    private File openedFile;

    @FXML
    public void initialize() {
        this.openedFile = new File(Paths.get("").toAbsolutePath() + "/project/example.php");
//        this.openedFile = new File(Paths.get("").toAbsolutePath() + "\\project\\short-example.php");
        this.updateStyledContentOpenedFile();
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
}
