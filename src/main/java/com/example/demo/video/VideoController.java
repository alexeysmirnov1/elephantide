package com.example.demo.video;

import com.example.demo.ide.Domain.Editor.Services.ContentStylist;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Stage;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
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
    public Label title2;
    @FXML
    public Label title3;
    @FXML
    public Label title4;
    @FXML
    public Label title5;

    @FXML
    public void initialize() {
        this.codeEditor.setStyle("-fx-highlight-fill: #0096c9; -fx-highlight-text-fill: white;");
//        this.title.setText("smirnov@flagsoft: ~");
//        this.title2.setText(".env");
//        this.title3.setText("Building.php");
//        this.title4.setText("BuildingRepository.php");

//        this.title.setText("smirnov@flagsoft: ~");
//        this.title2.setText("BuildingRepository.php");
//        this.title3.setText("scout.php");
//        this.title.setText("Building.php");

        this.title.setText("smirnov@flagsoft: ~");
        this.title2.setText("docker-compose.yml");
//        this.title3.setText(".env");
        this.makeLineNumbers();

        this.codeEditor.setOnKeyPressed(event -> this.fixTyped(event));
        this.codeEditor.setOnKeyTyped(event -> this.updateHighlight());
    }

    public void fixTyped(KeyEvent event) {
        System.out.println(event.getCode().getName());

        if(event.getCode().getName().equals("Tab")) {
            Integer rememberPosition = this.codeEditor.caretPositionProperty().getValue();
            this.codeEditor.replace(rememberPosition-1, rememberPosition, "    ", "");
        }
        if(event.getCode().getName().equals("Enter")) {
            System.out.println(this.codeEditor.getCurrentLineStartInParargraph());
            Integer rememberPosition = this.codeEditor.caretPositionProperty().getValue();
        }

        this.duplicateSymbol(event.getText(), "{", "}");
        this.duplicateSymbol(event.getText(), "[", "]");
        this.duplicateSymbol(event.getText(), "(", ")");
        this.duplicateSymbol(event.getText(), "\"");
        this.duplicateSymbol(event.getText(), "'");

        System.out.println(this.codeEditor.getText());
        System.out.println("--");
//        this.updateHighlight();
    }

    public void updateHighlight() {
        this.stylist.styling(
                this.codeEditor,
                this.codeEditor.getText(),
                true
        );
    }

    protected void makeLineNumbers() {
        IntFunction<Node> numberFactory = LineNumberFactory.get(this.codeEditor);
        IntFunction<Node> graphicFactory = line -> {
            HBox hbox = new HBox(numberFactory.apply(line));
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPrefWidth(90);

            HBox margin = new HBox();
            margin.setPadding(new Insets(0, 0, 0, 10));
            margin.setStyle("-fx-border-color: #ffffff30;");
            margin.setStyle("-fx-border-width: 0 1 0 0;");
            hbox.getChildren().add(margin);
            return hbox;
        };

        this.codeEditor.setParagraphGraphicFactory(graphicFactory);
    }

    private void duplicateSymbol(String current, String symbol) {
        this.duplicateSymbol(current, symbol, symbol);
    }

    private void duplicateSymbol(String current, String symbol, String doubl) {
        if(current.equals(symbol)) {
            Integer rememberPosition = this.codeEditor.caretPositionProperty().getValue();

            this.codeEditor.insertText(rememberPosition, doubl);
            this.codeEditor.moveTo(rememberPosition);
        }
    }
}
