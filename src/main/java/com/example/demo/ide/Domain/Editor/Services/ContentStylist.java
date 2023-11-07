package com.example.demo.ide.Domain.Editor.Services;

import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.VO.StyledToken;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.stereotype.Component;

@Component
public final class ContentStylist {
    public void styling(StyleClassedTextArea text, StyledTokenizedFile file) {
        text.clear();

        for (StyledToken styledToken: file.styledContent()) {
            text.append(
                styledToken.token(),
                styledToken.styleClass()
            );
        }
    }
}
