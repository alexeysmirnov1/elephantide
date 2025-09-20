package com.example.demo.ide.Domain.Editor.Services;

import com.example.demo.antlr.PhpLexer;
import com.example.demo.ide.Domain.Editor.Entities.Files.File;
import com.example.demo.ide.Domain.Editor.Entities.Files.StyledTokenizedFile;
import com.example.demo.ide.Domain.Editor.VO.StyledToken;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Component
public final class ContentStylist {
    private Map<Integer, String> styles = new HashMap<>() {{
        put(4, "PHPStart");
        put(40, "MultiLineComment");
        put(41, "SingleLineComment");
        put(240, "SingleLineComment");
        put(42, "ShellStyleComment");

        put(231, "php-string");
        put(232, "php-string");
        put(233, "php-string");

        put(45, "php-type");
        put(47, "php-type");
        put(51, "php-type");
        put(61, "php-type");
        put(62, "php-type");
        put(79, "php-type");
        put(105, "php-type");
        put(106, "php-type");
        put(119, "php-type");

        put(63, "php-function");
        put(66, "php-function");
        put(74, "php-function");
        put(97, "php-function");
        put(127, "php-function");

        put(44, "php-keyword");
        put(46, "php-keyword");
        put(50, "php-keyword");
        put(52, "php-keyword");
        put(53, "php-keyword");
        put(54, "php-keyword");
        put(56, "php-keyword");
        put(57, "php-keyword");
        put(58, "php-keyword");
        put(59, "php-keyword");
        put(60, "php-keyword");
        put(64, "php-keyword");
        put(65, "php-keyword");
        put(67, "php-keyword");
        put(75, "php-keyword");
        put(76, "php-keyword");
        put(77, "php-keyword");
        put(78, "php-keyword");
        put(80, "php-keyword");
        put(81, "php-keyword");
        put(82, "php-keyword");
        put(83, "php-keyword");
        put(84, "php-keyword");
        put(85, "php-keyword");
        put(86, "php-keyword");
        put(102, "php-keyword");
        put(103, "php-keyword");
        put(104, "php-keyword");
        put(107, "php-keyword");
        put(110, "php-keyword");
        put(111, "php-keyword");
        put(112, "php-keyword");
        put(113, "php-keyword");
        put(114, "php-keyword");
        put(115, "php-keyword");
        put(116, "php-keyword");
        put(117, "php-keyword");
        put(118, "php-keyword");
        put(120, "php-keyword");
        put(121, "php-keyword");
        put(123, "php-keyword");
        put(128, "php-keyword");
        put(129, "php-keyword");
        put(225, "Label");

        put(158, "php-const");
        put(159, "php-const");
        put(160, "php-const");
    }};

    public void styling(StyleClassedTextArea text, String content, boolean fullScan) {
        CharStream charStream = CharStreams.fromString(content);

        PhpLexer lexer = new PhpLexer(charStream);

        for (Token token: lexer.getAllTokens()) {
            text.setStyleClass(
                token.getStartIndex(),
                token.getStopIndex()+1,
                    this.styles.getOrDefault(token.getType(), "default")
            );
        }
    }

    private void findStartLine(StyleClassedTextArea text)
    {
        String fullText = text.getText();
        int caretPosition = text.getCaretPosition();

        fullText.lines();
    }
}
