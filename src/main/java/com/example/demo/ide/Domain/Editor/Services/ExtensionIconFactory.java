package com.example.demo.ide.Domain.Editor.Services;

import com.example.demo.ide.Presentation.Editor.Views.EditorView;
import com.google.common.io.Files;
import javafx.scene.image.Image;

public class ExtensionIconFactory {
    public static Image fileIcon(java.io.File file) {
        String extension = Files.getFileExtension(file.getName());

        switch (extension) {
            case "php": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/php.png").toExternalForm()
            );
            case "md": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/md.png").toExternalForm()
            );
            case "json": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/json.png").toExternalForm()
            );
            case "js": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/js.png").toExternalForm()
            );
            case "css": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/css.png").toExternalForm()
            );
            case "html": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/html.png").toExternalForm()
            );
            case "jpg":
            case "jpeg":
            case "png": return new Image(
                EditorView.class.getClassLoader().getResource("images/extensions/image.png").toExternalForm()
            );
        }

        return new Image(
            EditorView.class.getClassLoader().getResource("images/extensions/file.png").toExternalForm()
        );
    }
}
