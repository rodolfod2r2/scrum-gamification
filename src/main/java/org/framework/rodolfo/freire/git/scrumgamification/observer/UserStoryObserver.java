package org.framework.rodolfo.freire.git.scrumgamification.observer;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;

public class UserStoryObserver implements Observer {

    private UserStory userStory;

    public UserStoryObserver(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public void update() {
        System.out.println("User story updated: " + userStory.getId());
    }
}
