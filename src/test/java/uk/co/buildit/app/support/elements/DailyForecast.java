package uk.co.buildit.app.support.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class DailyForecast extends Application {
    public static WebElement getDateElement(int day) throws Exception {
        return getDailyElement("date", day);
    }

    public static WebElement getConditionElement(int day) throws Exception {
        return getDailyElement("description", day);
    }

    public static WebElement getWindSpeedElement(int day) throws Exception {
        return getDailyElement("speed", day);
    }

    public static WebElement getWindDirectionElement(int day) throws Exception {
        return getDailyElement("direction", day).findElement(By.tagName("svg"));
    }

    public static WebElement getRainfallElement(int day) throws Exception {
        return getDailyElement("rainfall", day);
    }

    public static WebElement getPressureElement(int day) throws Exception {
        return getDailyElement("pressure", day);
    }

    public static WebElement getMinTemperatureElement(int day) throws Exception {
        return getDailyElement("minimum", day);
    }

    public static WebElement getMaxTemperatureElement(int day) throws Exception {
        return getDailyElement("maximum", day);
    }

    private static WebElement getDailyElement(String name, int day)
            throws Exception {
        String locator = String.format("%s-%d", name, day);
        return getTestElement(locator);
    }
}