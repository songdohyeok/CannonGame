package com.nhnacademy.cannongame;

public class LevelManager {
    private int currentLevel = 0;

    public void nextLevel() { currentLevel++; }
    public int getCurrentLevel() { return currentLevel; }
}
