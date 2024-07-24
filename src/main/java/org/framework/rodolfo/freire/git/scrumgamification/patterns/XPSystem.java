package org.framework.rodolfo.freire.git.scrumgamification.patterns;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;

public class XPSystem {

    private static XPSystem instance;

    private XPSystem() {
    }

    public static XPSystem getInstance() {
        if (instance == null) {
            instance = new XPSystem();
        }
        return instance;
    }

    public void awardXP(Developer developer, int xpAmount) {
        int currentXP = developer.getXP();
        developer.setXP(currentXP + xpAmount);
    }

    public void deductXP(Developer developer, int xpAmount) {
        int currentXP = developer.getXP();
        developer.setXP(currentXP - xpAmount);
    }

}
