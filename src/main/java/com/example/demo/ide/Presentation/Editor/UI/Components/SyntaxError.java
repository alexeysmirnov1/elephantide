package com.example.demo.ide.Presentation.Editor.UI.Components;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class SyntaxError extends Line {
    public SyntaxError(int line, int start, int length) {
        super(start, line, length, line);

        this.setTranslateX(start);
        this.setTranslateY(line);

        this.getStrokeDashArray().addAll(4d);
        this.setStroke(Paint.valueOf("red"));
    }
}
