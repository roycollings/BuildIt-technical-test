package uk.co.buildit.app.support.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import uk.co.buildit.app.support.utilities.drivers.phantomjs;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static uk.co.buildit.app.support.utilities.CodeHelpers.tryUntilTimeout;

public class BrowserUtils {
    private static Logger logger = LogManager.getLogger(BrowserUtils.class);

    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void startBrowserAndNavigateToApplicationWebSite() throws Exception {
        startBrowser();

        String url = RuntimeSettings.getApplicationUrl();
        webDriver.get(url);
        logger.info("Request to navigate to " + url);
    }

    public static void quitBrowser() {
        logger.info("Closing web driver");

        try {
            if (null != webDriver) webDriver.quit();
        } catch (Exception e) {
            logger.warn("Unable to close browser: " + e.getMessage());
        }
    }

    /**
     * Returns elements using a selector.
     */
    public static List<WebElement> getElements(By by) throws Exception {
        logger.info("Find elements using " + by.toString() + "");
        List<WebElement> els = tryUntilTimeout(
                () -> webDriver.findElements(by),
                RuntimeSettings.getFindElementTimeoutSeconds(),
                "Cannot find elements using selector: " + by.toString()
        );

        return els;
    }

    /**
     * Returns an element using a selector.
     */
    public static WebElement getElement(By by) throws Exception {
        return getElements(by).get(0);
    }

    private static void deleteAllCookies() {
        logger.info("Delete all cookies");
        webDriver.manage().deleteAllCookies();
    }

    private static void setDriver(WebDriver driver) throws Exception {
        webDriver = driver;

        webDriver.manage().timeouts().pageLoadTimeout(
                RuntimeSettings.getPageLOadTimeoutSeconds(),
                TimeUnit.SECONDS
        );
    }

    private static void startBrowser() throws Exception {
        String driverType = RuntimeSettings.getWebDriverType();

        logger.info("Start browser using " + driverType + " web driver");

        switch (driverType) {
            case "phantomjs":
            default:
                setDriver(phantomjs.launch());
                break;
        }

        deleteAllCookies();
    }
}
