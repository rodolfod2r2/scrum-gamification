package org.framework.rodolfo.freire.git.scrumgamification.observer;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;

public class SprintBacklogObserver implements Observer {

    private SprintBacklog sprintBacklog;

    public SprintBacklogObserver(SprintBacklog sprintBacklog) {
        this.sprintBacklog = sprintBacklog;
    }

    @Override
    public void update() {
        System.out.println("Sprint backlog updated: " + sprintBacklog.getId());
    }
}
