package org.framework.rodolfo.freire.git.scrumgamification;

import org.framework.rodolfo.freire.git.scrumgamification.integration.*;
import org.framework.rodolfo.freire.git.scrumgamification.performance.*;
import org.framework.rodolfo.freire.git.scrumgamification.unit.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        DeveloperIntegrationTest.class,
        ProductBacklogIntegrationTest.class,
        ScrumMasterIntegrationTest.class,
        SprintBacklogIntegrationTest.class,
        UserStoryIntegrationTest.class,
        DeveloperPerformanceTest.class,
        ProductBacklogPerformanceTest.class,
        ScrumMasterPerformanceTest.class,
        SprintBacklogPerformanceTest.class,
        UserStoryPerformanceTest.class,
        DeveloperServiceTest.class,
        ProductBacklogServiceTest.class,
        ScrumMasterServiceTest.class,
        SprintBacklogServiceTest.class,
        UserStoryServiceTest.class
})
class ScrumGamificationApplicationTests {
}

