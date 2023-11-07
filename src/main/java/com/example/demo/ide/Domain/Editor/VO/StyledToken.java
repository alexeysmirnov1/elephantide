package com.example.demo.ide.Domain.Editor.VO;

public final class StyledToken {
    private final Token token;
    private final String styleClass;

    public StyledToken(Token token, String styleClass) {
        this.token = token;
        this.styleClass = styleClass;
    }

    public String token() {
        return this.token.getToken();
    }

    public String styleClass() {
        return this.styleClass;
    }

    public int line() {
        return this.token.getLine();
    }
}
