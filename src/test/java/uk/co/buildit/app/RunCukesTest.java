package uk.co.buildit.app;

import com.cucumber.listener.ExtentCucumberFormatter;
import uk.co.buildit.app.support.utilities.RuntimeSettings;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = false,
        plugin = {
                "pretty",
                "com.cucumber.listener.ExtentCucumberFormatter"
        },
        features = "features",
        tags = {"~@ignore"}
)
public class RunCukesTest {
    @BeforeClass
    public static void setup() throws Exception {
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File("reports/index.html"));
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

        ExtentCucumberFormatter.addSystemInfo("Application URL", RuntimeSettings.getApplicationUrl());
        ExtentCucumberFormatter.addSystemInfo("Webdriver", RuntimeSettings.getWebDriverType());
        ExtentCucumberFormatter.addSystemInfo("Cucumber options", RuntimeSettings.getCucumberOptions());
    }
}
