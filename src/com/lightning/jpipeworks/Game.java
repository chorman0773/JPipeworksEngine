package com.lightning.jpipeworks;

public abstract class Game {
    public static enum GameState {
        MAIN_GAME
    }
    
    public void loadState(Engine engine, GameState state) {}
}
