package uk.co.buildit.app.support.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import uk.co.buildit.app.support.data.TestData;
import uk.co.buildit.app.support.utilities.BrowserUtils;
import uk.co.buildit.app.support.utilities.RuntimeSettings;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static uk.co.buildit.app.support.elements.Application.*;

public class Application {
    public static void checkApplicationIsLaunched() throws Exception {
        String expectedUrl = RuntimeSettings.getApplicationUrl();
        String actualUrl = BrowserUtils.getDriver().getCurrentUrl();

        assertEquals(
                "Application is launched",
                expectedUrl,
                actualUrl
        );
    }

    public static void setCurrentCity() throws Exception {
        String city = getCityElement().getAttribute("value");

        TestData.setCity(city);
    }

    protected static String roundDownToString(BigDecimal value) {
        String expected = value.toString();
        return expected.replaceAll("\\..*$", "");
    }

    protected static String getDate(WebElement element) throws Exception {
        return element.getText();
    }

    protected static String getCondition(WebElement element) throws Exception {
        return element.getAttribute("aria-label");
    }

    protected static String getMaxTemperature(WebElement element) throws Exception {
        String value = element.getText();

        // Drop the 'degree' symbol.
        return value.substring(0, value.length() - 1);
    }

    protected static String getMinTemperature(WebElement element) throws Exception {
        String value = element.getText();

        // Drop the 'degree' symbol.
        return value.substring(0, value.length() - 1);
    }

    protected static String getWindSpeed(WebElement element) throws Exception {
        String value = element.getText();

        // Drop the unit of measurement.
        return value.replaceAll("[a-zA-Z]*", "");
    }

    protected static String getWindDirection(WebElement element) throws Exception {
        String style = element.getAttribute("style");

        Pattern pattern = Pattern.compile("rotate\\(([0-9]*)deg\\)");

        Matcher matcher = pattern.matcher(style);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    protected static String getRainfall(WebElement element) throws Exception {
        String value = element.getText();

        // Drop the unit of measurement.
        return value.replaceAll("[a-zA-Z]*", "");
    }

    protected static String getPressure(WebElement element) throws Exception {
        String value = element.getText();

        // Drop the unit of measurement.
        return value.replaceAll("[a-zA-Z]*", "");
    }
}
