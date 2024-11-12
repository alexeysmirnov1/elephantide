package com.example.demo.ide.Presentation.Editor.UI.Components;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ClassNotFound extends Rectangle {
    public ClassNotFound(int line, int start, int length) {
        super(0,0, length, 24);

        this.setTranslateX(start);
        this.setTranslateY(line * 20);

        this.setFill(Paint.valueOf("cbd7627d"));
    }
}
