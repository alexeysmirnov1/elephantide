package com.example.demo.ide.Domain.Editor.Entities.Files;

import com.example.demo.ide.Domain.Editor.VO.Token;
import java.util.ArrayList;

public final class TokenizedFile {
    final private File file;

    private final ArrayList<Token> tokens = new ArrayList<>();
    private String token = "";

    private boolean hasInterfaces = false;
    private boolean classStarted = false;

    public TokenizedFile(File file) {
        this.file = file;
    }

    public ArrayList<Token> tokens() {
        if(!this.tokens.isEmpty()) {
            return this.tokens;
        }

        boolean openQ = false;

        for(int l = 0; l < this.file.lines().size(); l++) {
            for (int s = 0; s < this.lineLength(l); s++) {
                char ch = this.file.lines().get(l).charAt(s);

                if(ch == '\'') {
                    openQ = !openQ;
                }

                if(!openQ && this.breakingToken(ch)) {
                    if (!this.token.equals("")) {
                        this.addToken(l, s);
                    }

                    this.addToken(String.valueOf(ch), l, this.lineLength(l));
                } else {
                    this.growToken(ch);
                }
            }

            if(!this.token.equals("")) {
                this.addToken(l, this.lineLength(l));
            }

            this.addToken("\n", l, 1);
        }

        return this.tokens;
    }

    private String type(String token) {
        final int countTokens = this.tokens.size();
        if (countTokens > 1) {
            Token lastToken = this.tokens.get(countTokens - 2);
            switch (lastToken.getToken()) {
                case "class": return "CLASS";
                case "function": return "FUNCTION";
                case "->": return "PROPERTY";
                case "::": return "CONST";
                case "namespace": return "NAMESPACE";
                case "use": return "INCLUDECLASS";
                case "extends": return "PARENTCLASS";
            }
        }

        if(token.startsWith("'")) {
            if(token.endsWith("'")) {
                return "STRING";
            }
        }

        if(this.numericToken(token)) {
            return "NUMERIC";
        }

        switch (token) {
            case " ":
                return "SPACE";
            case "<?php":
                return "OPEN_TAG";
            case "<?":
                return "SHORT_TAG";
            case "<?=":
                return "SHORT_PRINT_TAG";
            case "?>":
                return "CLOSE_TAG";
            case "public":
            case "protected":
            case "private":
                return "MODIFICATOR";
            case "readonly":
                return "READ_MODIFICATOR";
            case "namespace":
            case "use":
            case "class":
            case "function":
            case "return":
            case "extends":
            case "new":
                return "KEYWORD";
            case "implements":
                this.hasInterfaces = true;
                return "KEYWORD";
            case "string":
            case "int":
            case "float":
            case "bool":
            case "object":
            case "null":
            case "void":
            case "resource":
                return "TYPE";
            case "$this":
            case "self":
            case "parent":
                return "CLASSLINK";
            case ";":
                return "INSTRUCTION";
            case ",":
                return "COMMA";
            case ":":
                return "COLON";
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                return "OPERAND";
            case "=":
                return "ASSIGN";
            case "{":
                this.classStarted = true;
                return "OPENBLOCK";
            case "}":
                return "ENDBLOCK";
            case "\n":
                return "BREAK";
            case "(":
                if (countTokens > 0) {
                    int prevIndex = countTokens - 1;
                    Token lastToken = this.tokens.get(prevIndex);

                    switch (lastToken.getType()) {
                        case "PROPERTY":
                            lastToken.changeType("METHOD");
                            break;
                        case "CONST":
                            lastToken.changeType("STATICMETHOD");
                            break;
                        case "NONE":
                            lastToken.changeType("FUNCTION");
                            break;
                    }
                }
                return "OPENBRACKET";
            case ")":
                return "ENDBRACKET";
            case "[":
                return "OPENARRAY";
            case "]":
                return "ENDARRAY";
            case "->":
            case "::":
                int prevIndex = countTokens - 1;
                Token lastToken = this.tokens.get(prevIndex);
                if (!lastToken.getType().equals("ENDBRACKET")) {
                    lastToken.changeType("CLASS");
                }
                return "CHAIN";
        }

        if(token.startsWith("$")) {
            int prevIndex = countTokens - 1;
            Token lastToken = this.tokens.get(prevIndex);
            if (lastToken.getType().equals("NONE")) {
                lastToken.changeType("TYPE");
            }
            return "VAR";
        }

        if(this.hasInterfaces && !this.classStarted) {
            return "INTERFACE";
        }

        return "NONE";
    }

    private Token makeToken(String token, int line, int position) {
        return new Token(
            token,
            this.type(token),
            new int[]{
                line,
                position - token.length()
            }
        );
    }

    private boolean breakingToken(char token) {
        switch(token) {
            case ' ':
            case '(':
            case ')':
            case '[':
            case ']':
            case ',':
            case ';':
            case '=':
            case ':':
                return true;
            default: return false;
        }
    }

    private void addToken(int line, int symbol) {
        this.addToken(this.token, line, symbol);
        this.token = "";
    }

    private void addToken(String token, int line, int symbol) {
        if(token.contains("->") && token.length() > 2) {
            String[] chains = token.split("->", 2);
            if(!chains[0].isEmpty()) {
                this.tokens.add(
                    this.makeToken(chains[0], line, symbol)
                );
            }

            this.tokens.add(
                this.makeToken("->", line, symbol)
            );
            this.tokens.add(
                this.makeToken(chains[1], line, symbol)
            );

        } else if (token.contains("::") && token.length() > 2) {
            String[] chains = token.split("::", 2);

            this.tokens.add(
                this.makeToken(chains[0], line, symbol)
            );
            this.tokens.add(
                this.makeToken("::", line, symbol)
            );
            this.tokens.add(
                this.makeToken(chains[1], line, symbol)
            );
        } else {
            this.tokens.add(
                this.makeToken(token, line, symbol)
            );
        }
    }

    private int lineLength(int line) {
        return this.file.lines().get(line).length();
    }

    private void growToken(char ch) {
        this.token += ch;
    }

    private Boolean numericToken(String token) {
        try {
            Double.valueOf(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
