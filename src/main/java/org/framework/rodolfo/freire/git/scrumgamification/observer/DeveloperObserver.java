package org.framework.rodolfo.freire.git.scrumgamification.observer;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;

public class DeveloperObserver implements Observer {

    private Developer developer;

    public DeveloperObserver(Developer developer) {
        this.developer = developer;
    }

    @Override
    public void update() {
        System.out.println("Developer XP updated: " + developer.getXP());
    }
}
