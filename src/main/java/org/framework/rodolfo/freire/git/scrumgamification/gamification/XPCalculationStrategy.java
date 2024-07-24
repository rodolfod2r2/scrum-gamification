package org.framework.rodolfo.freire.git.scrumgamification.gamification;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;

public interface XPCalculationStrategy {
    void calculateXP(Developer developer, int taskPoints);

    int calculateLevelBasedXP(Developer developer, int tasksCompleted);
}
