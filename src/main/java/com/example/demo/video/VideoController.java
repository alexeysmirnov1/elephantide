package com.example.demo.video;

import com.example.demo.database.postgresql.Presentation.UI.Connections;
import com.example.demo.docker.Presentation.UI.DockerServicesTable;
import com.example.demo.git.Presentation.UI.Git;
import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Stage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.collection.ListModification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

@Component
public class VideoController extends Controller {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;

    @Autowired
    private ContentStylist stylist;

    @FXML
    public StyleClassedTextArea codeEditor;

    @FXML
    public Label title;

    @FXML
    public void initialize() {
        this.codeEditor.setStyle("-fx-highlight-fill: #0096c9; -fx-highlight-text-fill: white;");
        this.title.setText("Building.php");
        this.makeLineNumbers();
    }

    public void updateHighlight() {
        this.stylist.styling(
                this.codeEditor,
                this.codeEditor.getText(),
                true
        );

//        this.makeLineNumbers();
    }

    protected void makeLineNumbers()
    {
        IntFunction<Node> numberFactory = LineNumberFactory.get(this.codeEditor);
        IntFunction<Node> graphicFactory = line -> {
            HBox hbox = new HBox(numberFactory.apply(line));
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPrefWidth(50);

            HBox margin = new HBox();
            margin.setPadding(new Insets(0, 0, 0, 20));
            margin.setStyle("-fx-border-color: #ffffff30;");
            margin.setStyle("-fx-border-width: 0 1 0 0;");
            hbox.getChildren().add(margin);
            return hbox;
        };

//        this.codeEditor.setParagraphGraphicFactory(graphicFactory);
        this.codeEditor.setParagraphGraphicFactory(graphicFactory);
    }
}
