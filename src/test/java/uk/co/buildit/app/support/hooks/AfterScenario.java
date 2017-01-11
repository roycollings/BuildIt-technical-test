package uk.co.buildit.app.support.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uk.co.buildit.app.support.utilities.BrowserUtils;

import static uk.co.buildit.app.support.utilities.Scenario.*;

public class AfterScenario {
    private static Logger logger = LogManager.getLogger(AfterScenario.class);

    @After
    public void after(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                addScreenShotToTestReport(scenario);
            } catch (Exception e) {
                logger.warn("Unable to attach screen shots etc... : " + e.getMessage());
            }
        }

        BrowserUtils.quitBrowser();
    }
}