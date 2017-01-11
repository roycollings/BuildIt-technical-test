package uk.co.buildit.app.support.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ThreeHourlyForecast extends Application {
    public static WebElement getHourElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("hour", day, itemNumber);
    }

    public static WebElement getConditionElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("description", day, itemNumber);
    }

    public static WebElement getWindSpeedElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("speed", day, itemNumber);
    }

    public static WebElement getWindDirectionElement(int day, int itemNumber) throws Exception{
        WebElement parent = getThreeHourlyElement("direction", day, itemNumber);

        return parent.findElement(By.tagName("svg"));
    }

    public static WebElement getRainfallElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("rainfall", day, itemNumber);
    }

    public static WebElement getPressureElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("pressure", day, itemNumber);
    }

    public static WebElement getMinTemperatureElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("minimum", day, itemNumber);
    }

    public static WebElement getMaxTemperatureElement(int day, int itemNumber) throws Exception{
        return getThreeHourlyElement("maximum", day, itemNumber);
    }

    private static WebElement getThreeHourlyElement(String name, int day, int itemNumber)
            throws Exception{
        String locator = String.format("%s-%d-%d", name, day, itemNumber);

        return getTestElement(locator);
    }
}