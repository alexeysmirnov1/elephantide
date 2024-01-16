package com.example.demo.ide.Presentation.Editor.Views;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Files.TokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Presentation.Editor.UI.Components.Directory;
import com.example.demo.ide.UI.Component;
import com.example.demo.ide.UI.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class EditorView {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;

    @Autowired
    private ContentStylist stylist;

    @FXML
    protected HBox tabPanel;

    @FXML
    protected StyleClassedTextArea codeEditor;

    @FXML
    protected VBox projectDiscover;

    protected void initProjectDiscover(Project project) {
        for(java.io.File file: project.files()) {
            HBox component = this.makeComponentFromFile(file);

            this.projectDiscover.getChildren().add(component);
        }
    }

    protected void updateEditor(File file) {
        TokenizedFile tokenizedFile = new TokenizedFile(file);
        StyledTokenizedFile styledTokenizedFile = new StyledTokenizedFile(tokenizedFile);

        int caretCurrentPosition = this.codeEditor.getCaretPosition();

        this.stylist.styling(
            this.codeEditor,
            styledTokenizedFile
        );

        this.codeEditor.moveTo(caretCurrentPosition);
    }

    protected void updateTabs(FixedList<Tab> tabs) {
        this.tabPanel.getChildren().clear();

        for(Tab tab: tabs.getItems()) {
            VBox tabComponent = (VBox) this.context.getBean(com.example.demo.ide.Presentation.Editor.UI.Components.Tab.class).load();
            Label property = (Label) tabComponent.lookup("#title");
            property.setText(tab.name());

            this.tabPanel.getChildren().add(tabComponent);
        }
    }

    private HBox makeComponentFromFile(java.io.File file) {
        if (file.isDirectory()) {
            HBox dirComponent = (HBox) this.context.getBean(Directory.class).load();
            Label label = (Label) dirComponent.lookup("#dirName");
            label.setText(file.getName());
            label.setOnMouseClicked(event -> this.openCloseDirectory(dirComponent, file));
            return dirComponent;
        } else {
            HBox fileComponent = (HBox) this.context.getBean(com.example.demo.ide.Presentation.Editor.UI.Components.File.class).load();
            Label label = (Label) fileComponent.lookup("#fileName");
            label.setText(file.getName());
            fileComponent.setOnMouseClicked(event -> this.openFile(file.getPath()));
            return fileComponent;
        }
    }

    protected void openCloseDirectory(HBox directory, java.io.File path) {
        com.example.demo.ide.Domain.Editor.Entities.Directory dir = new com.example.demo.ide.Domain.Editor.Entities.Directory(path.getPath());

        VBox children = (VBox) directory.lookup("#children");
        if(children.getChildren().stream().count() > 0) {
            children.getChildren().clear();
        } else {
            for(java.io.File item: dir.files()) {
                HBox component = this.makeComponentFromFile(item);
                children.getChildren().add(component);
            }
        }
    }

    protected abstract void openFile(String filePath);
}
