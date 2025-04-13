package com.example.demo.ide.editor;

import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.VO.StyledToken;
import com.example.demo.ide.Presentation.Editor.UI.Components.ClassNotFound;
import com.example.demo.ide.Presentation.Editor.UI.Components.SyntaxError;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.Caret;
import org.fxmisc.richtext.CaretNode;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.IOException;

public class CodeEditor extends StackPane {
    private final int lineHeight = 20;
    private final int symbolWidth = 5;

    private StyleClassedTextArea editor = new StyleClassedTextArea();

    public CodeEditor() {
        this.getChildren().add(0, new VirtualizedScrollPane<>(this.editor));
        this.editor.getStyleClass().add("editor");
//
        CaretNode caret = new CaretNode("caret", this.editor);
        caret.setStroke(Paint.valueOf("white"));
//
//        this.editor.removeCaret(caret);
//        editor.showCaretProperty().setValue(Caret.CaretVisibility.OFF);
//        System.out.println(this.editor.getShowCaret());
//        System.out.println(this.editor.showCaretProperty());
//        this.editor.addCaret(caret);

//        EditorIntegration editor = new EditorIntegration();
//        editor.initialize();

        // Пример использования
//        editor.onTextChanged(
//            "./project/example.php",
//            "<?php echo 'Hello World';"
//        );

//        editor.scheduleTextUpdate(
//            "./project/example.php",
//            "<?php echo 'Hello World';"
//        );
    }

    public StyleClassedTextArea setTextArea() {
        return this.editor;
    }

    public String getText() {
        return this.editor.getText();
    }

    public void setText(StyledTokenizedFile styled) {
        this.editor.clear();

//        int caretCurrentPosition = this.editor.caret;
//        System.out.println(caretCurrentPosition);
//        this.editor.addCaret(new CaretNode("caret", this.editor));

        for (StyledToken styledToken: styled.styledContent()) {
            this.editor.append(
                styledToken.token(),
                styledToken.styleClass()
            );
        }

//        this.editor.moveTo(caretCurrentPosition);
    }

    public void onKeyTyped(EventHandler<? super KeyEvent> handler) {
        this.editor.setOnKeyTyped(handler);
    }

    private void addErrorSyntax(int line, int startSymbol, int lengthError) {
        SyntaxError error = new SyntaxError(line * this.lineHeight, startSymbol * this.symbolWidth, lengthError * this.symbolWidth);
        this.getChildren().add(error);
    }

    private void addClassNotFoundError(int line, int startSymbol, int lengthError) {
        ClassNotFound notFound = new ClassNotFound(line * this.lineHeight, startSymbol * this.symbolWidth, lengthError * this.symbolWidth);
        this.getChildren().add(notFound);
    }
}
