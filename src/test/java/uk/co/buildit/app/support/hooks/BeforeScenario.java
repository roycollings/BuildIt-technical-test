package uk.co.buildit.app.support.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import uk.co.buildit.app.support.data.TestData;
import uk.co.buildit.app.support.utilities.BrowserUtils;

public class BeforeScenario {
    @Before
    public void before(Scenario scenario) throws Exception {
        BrowserUtils.startBrowserAndNavigateToApplicationWebSite();

        // Make sure all data is reset before each scenario.
        TestData.reset();
    }
}