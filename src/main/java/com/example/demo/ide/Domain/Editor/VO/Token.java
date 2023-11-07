package com.example.demo.ide.Domain.Editor.VO;

public final class Token {
    final private String token;
    final private String type;

    final private int[] position;

    public Token(String token, String type, int[] position) {
        this.token = token;
        this.type = type;
        this.position = new int[] {position[0], position[1]};
    }

    public String getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

    public int getLine() {
        return this.position[0];
    }

    public Token changeType(String type) {
        return new Token(this.token, type, this.position);
    }

    @Override
    public String toString() {
        return "{ token: '" + this.token + "'; type: '" + this.type + "'; line: " + this.position[0] + "-" + this.position[1] + " }";
    }
}
