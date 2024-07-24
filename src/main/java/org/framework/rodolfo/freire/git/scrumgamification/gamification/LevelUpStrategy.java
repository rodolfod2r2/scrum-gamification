package org.framework.rodolfo.freire.git.scrumgamification.gamification;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;

public interface LevelUpStrategy {
    int calculateLevel(int experiencePoints);

    void levelUp(Developer developer, int xp);

}
