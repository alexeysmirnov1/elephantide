package com.example.demo.ide.Presentation.Editor.UI.Components;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class SyntaxError extends Line {
    public SyntaxError(int line, int start, int length) {
        super(start, line * 20, length, line * 20);

        this.setTranslateX(start);
        this.setTranslateY(line * 20);

        this.getStrokeDashArray().addAll(4d);
        this.setStroke(Paint.valueOf("red"));
    }
}
