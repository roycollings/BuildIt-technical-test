package uk.co.buildit.app.support.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Scenario {
    /**
     * @param scenario
     */
    public static void addScreenShotToTestReport(cucumber.api.Scenario scenario) {
        scenario.embed(
                ((TakesScreenshot) BrowserUtils.getDriver()).getScreenshotAs(OutputType.BYTES),
                "image/png"
        );
    }

    /**
     * @param scenario
     * @param text
     */
    public static void addTextToTestReport(cucumber.api.Scenario scenario, String text) {
        scenario.write(text);
    }
}
