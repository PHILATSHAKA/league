package com.span;

public enum GameRules {
    LOSS(0),
    DRAW(1),
    WIN(3);

    private final int value;

    GameRules(final int newValue) {
        value = newValue;
    }

    public int getRule() {
        return value;
    }
}
