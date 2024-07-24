package org.framework.rodolfo.freire.git.scrumgamification.gamification;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;

public class LevelBasedXPCalculationStrategy implements XPCalculationStrategy {
    @Override
    public void calculateXP(Developer developer, int taskPoints) {
        int currentXP = developer.getXP();
        developer.setXP(currentXP + taskPoints * 10);
    }

    @Override
    public int calculateLevelBasedXP(Developer developer, int tasksCompleted) {
        int baseXP = tasksCompleted * 50;
        int levelModifier = developer.getLevel() * 100;
        return baseXP + levelModifier;
    }
}
