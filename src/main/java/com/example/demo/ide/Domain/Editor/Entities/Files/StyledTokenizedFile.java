package com.example.demo.ide.Domain.Editor.Entities.Files;

import com.example.demo.ide.Domain.Editor.VO.StyledToken;
import com.example.demo.ide.Domain.Editor.VO.Token;
import java.util.ArrayList;

public final class StyledTokenizedFile {
    private final TokenizedFile file;

    public StyledTokenizedFile(TokenizedFile file) {
        this.file = file;
    }

    public ArrayList<StyledToken> styledContent() {
        ArrayList<StyledToken> tokens = new ArrayList<>();

        for (Token token: this.file.tokens()) {
            tokens.add(new StyledToken(token, this.defineStyle(token)));
        }

        return tokens;
    }

    private String defineStyle(Token token) {
        switch (token.getType()) {
            case "TAGS": return "php-tags";
            case "NAMESPACE": return "php-namespace";
            case "INCLUDECLASS": return "php-included-classes";
            case "KEYWORD": return "php-keyword";
            case "CLASS": return "php-class";
            case "PARENTCLASS": return "php-extends-class";
            case "INTERFACE": return "php-implemented-interfaces";
            case "MODIFICATOR": return "php-modificator";
            case "TYPE": return "php-type";
            case "STRING": return "php-string-vars";
            case "NUMERIC": return "php-numeric-vars";
            case "FUNCTION": return "php-functions";
            case "METHOD": return "php-methods";
            default: return "default";
        }
    }
}
