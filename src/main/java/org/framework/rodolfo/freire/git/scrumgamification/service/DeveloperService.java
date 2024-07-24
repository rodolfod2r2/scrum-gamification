package org.framework.rodolfo.freire.git.scrumgamification.service;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.framework.rodolfo.freire.git.scrumgamification.gamification.LevelUpStrategy;
import org.framework.rodolfo.freire.git.scrumgamification.gamification.XPCalculationStrategy;
import org.framework.rodolfo.freire.git.scrumgamification.observer.DeveloperObserver;
import org.framework.rodolfo.freire.git.scrumgamification.observer.Observer;
import org.framework.rodolfo.freire.git.scrumgamification.observer.Subject;
import org.framework.rodolfo.freire.git.scrumgamification.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeveloperService extends Subject {

    private final DeveloperRepository developerRepository;
    private final XPCalculationStrategy xpCalculationStrategy;
    private LevelUpStrategy levelUpStrategy;

    private List<Observer> observers = new ArrayList<>();

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository, XPCalculationStrategy xpCalculationStrategy) {
        this.developerRepository = developerRepository;
        this.xpCalculationStrategy = xpCalculationStrategy;
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer getById(Long id) {
        return developerRepository.findById(id).orElse(null);
    }

    public Developer save(Developer developer) {
        Observer observer = new DeveloperObserver(developer);
        developer.registerObserver(observer);

        xpCalculationStrategy.calculateXP(developer, 10); // Example task points

        return developerRepository.save(developer);
    }

    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    public void assignXP(Developer developer, int tasksCompleted) {
        int xpEarned = xpCalculationStrategy.calculateLevelBasedXP(developer, tasksCompleted);
        developer.setXP(developer.getXP() + xpEarned);
        levelUpStrategy.levelUp(developer, xpEarned);
        notifyObservers();
    }

    private void notifyObservers(Developer developer) {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
