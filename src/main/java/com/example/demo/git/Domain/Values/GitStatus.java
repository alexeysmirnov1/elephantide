package com.example.demo.git.Domain.Values;

public enum GitStatus {
    ADDED,
    MODIFIED,
    UNTRACKED,
    CONFLICT,
    DELETED;

    public String color() {
        switch(this) {
            case ADDED: return "#11FF88";
            case MODIFIED: return "#3BA6FE";
            case UNTRACKED: return "#E83939";
            case CONFLICT: return "#FF0DEF";
            case DELETED: return "#5D5959";
            default: return "white";
        }
    }
}
