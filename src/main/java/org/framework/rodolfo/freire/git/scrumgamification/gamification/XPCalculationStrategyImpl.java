package org.framework.rodolfo.freire.git.scrumgamification.gamification;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.springframework.stereotype.Service;

@Service
public class XPCalculationStrategyImpl implements XPCalculationStrategy {

    @Override
    public void calculateXP(Developer developer, int taskPoints) {
        int currentXP = developer.getXP();
        developer.setXP(currentXP + taskPoints * 10);
    }

    @Override
    public int calculateLevelBasedXP(Developer developer, int tasksCompleted) {
        return tasksCompleted * 50;
    }

}
