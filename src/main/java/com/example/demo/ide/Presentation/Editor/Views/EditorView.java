package com.example.demo.ide.Presentation.Editor.Views;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Files.TokenizedFile;
import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Domain.Editor.Entities.Tab;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Domain.Editor.Services.ExtensionIconFactory;
import com.example.demo.ide.Domain.Editor.VO.FixedList;
import com.example.demo.ide.Presentation.Editor.UI.Components.Directory;
import com.example.demo.ide.UI.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    @FXML
    protected HBox discoverBox;
    @FXML
    protected Pane docker;

    public void showProject() {
        this.discoverBox.setTranslateX(0);
    }

    public void hideProject() {
        this.discoverBox.setTranslateX(-this.discoverBox.widthProperty().get());
    }

    protected abstract void openFile(String filePath);

    protected abstract void chooseTab(String filePath);

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

    protected void updateTabs(FixedList<Tab> tabs, Tab current) {
        this.tabPanel.getChildren().clear();

        for(Tab tab: tabs.getItems()) {
            VBox tabComponent = (VBox) this.context.getBean(com.example.demo.ide.Presentation.Editor.UI.Components.Tab.class).load();
            Label property = (Label) tabComponent.lookup("#title");
            property.setText(tab.name());

            if (!tab.equals(current)) {
                tabComponent.getStyleClass().add("tab-disable");
                tabComponent.setOnMouseClicked(event -> this.chooseTab(tab.toString()));
            }

            this.tabPanel.getChildren().add(tabComponent);
        }
    }

    protected void openCloseDirectory(HBox directory, java.io.File path) {
        com.example.demo.ide.Domain.Editor.Entities.Directory dir = new com.example.demo.ide.Domain.Editor.Entities.Directory(path.getPath());

        VBox children = (VBox) directory.lookup("#children");
        if(children.getChildren().stream().count() > 0) {
            ImageView directoryIcon = (ImageView) directory.lookup("#icon");
            directoryIcon.setImage(
                new Image(
                    EditorView.class.getClassLoader().getResource("images/closed-directory.png").toExternalForm()
                )
            );
            children.getChildren().clear();
        } else {
            ImageView directoryIcon = (ImageView) directory.lookup("#icon");
            directoryIcon.setImage(
                new Image(
                    EditorView.class.getClassLoader().getResource("images/opened-directory.png").toExternalForm()
                )
            );

            for(java.io.File item: dir.files()) {
                HBox component = this.makeComponentFromFile(item);
                children.getChildren().add(component);
            }
        }
    }

    private void open(String filePath) {
        this.openFile(filePath);

        this.hideProject();
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

            ImageView icon = (ImageView) fileComponent.lookup("#icon");
            icon.setImage(ExtensionIconFactory.fileIcon(file));

            fileComponent.setOnMouseClicked(event -> this.open(file.getPath()));
            return fileComponent;
        }
    }
}
