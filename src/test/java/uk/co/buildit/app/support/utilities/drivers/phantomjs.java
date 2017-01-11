package uk.co.buildit.app.support.utilities.drivers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import uk.co.buildit.app.support.utilities.RuntimeSettings;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class phantomjs {
    public static WebDriver launch() throws Exception {
        Logger
                .getLogger(PhantomJSDriverService.class.getName())
                .setLevel(Level.OFF);

        String[] capsList = new String[]{
                "--ssl-protocol=tlsv1",
                "--webdriver-loglevel=NONE",
                "--ignore-ssl-errors=true"
        };

        DesiredCapabilities caps = DesiredCapabilities.phantomjs();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, capsList);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX, "Y");

        // The standard 2.11 phantomJS has issues that the dockerised version doesn't, so we use that instead.
        WebDriver driver = new RemoteWebDriver(
                new URL("http://127.0.0.1:" + RuntimeSettings.getPhantomJSPort()),
                caps
        );

        driver.manage().window().setSize(new Dimension(3840, 3000));
        driver.manage().window().maximize();

        return driver;
    }
}
