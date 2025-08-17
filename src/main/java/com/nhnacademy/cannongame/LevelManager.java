package com.nhnacademy.cannongame;

import java.util.*;

public class LevelManager {
    private List<LevelConfig> levels = new ArrayList<>();
    private int currentLevel = 0;

    public enum BrickType { NORMAL }

    public LevelManager() {
        levels.add(new LevelConfig("시작", 5, 10, 1.0, Map.of(BrickType.NORMAL, 1.0)));
        levels.add(new LevelConfig("도전", 6, 10, 1.2, Map.of(BrickType.NORMAL, 0.6)));
        levels.add(new LevelConfig("난이도 상승", 7, 11, 1.3, Map.of(BrickType.NORMAL, 0.5)));
    }

    public LevelConfig getCurrentLevelConfig() {
        if (currentLevel >= levels.size()) return null;
        return levels.get(currentLevel);
    }

    public void nextLevel() { if (currentLevel < levels.size() - 1) currentLevel++; }
    public boolean isLastLevel() { return currentLevel == levels.size() - 1; }

    public BrickType selectBrickType(Map<BrickType, Double> probabilities) {
        double random = Math.random();
        double cumulative = 0;
        for (Map.Entry<BrickType, Double> entry : probabilities.entrySet()) {
            cumulative += entry.getValue();
            if (random < cumulative) return entry.getKey();
        }
        return probabilities.keySet().stream().reduce((first, second) -> second).orElse(BrickType.NORMAL);
    }

    public static class LevelConfig {
        private String name;
        private int rows, cols;
        private double ballSpeedMultiplier;
        private Map<BrickType, Double> brickProbabilities;

        public LevelConfig(String name, int rows, int cols, double ballSpeedMultiplier, Map<BrickType, Double> brickProbabilities) {
            this.name = name;
            this.rows = rows;
            this.cols = cols;
            this.ballSpeedMultiplier = ballSpeedMultiplier;
            this.brickProbabilities = brickProbabilities;
        }

        public String getName() { return name; }
        public int getRows() { return rows; }
        public int getCols() { return cols; }
        public double getBallSpeedMultiplier() { return ballSpeedMultiplier; }
        public Map<BrickType, Double> getBrickProbabilities() { return brickProbabilities; }
    }
}
