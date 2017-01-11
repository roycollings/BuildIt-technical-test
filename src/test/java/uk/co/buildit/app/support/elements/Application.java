package uk.co.buildit.app.support.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static uk.co.buildit.app.support.utilities.BrowserUtils.*;

public class Application {
    public static WebElement getCityElement() throws Exception {
        return getElement(By.id("city"));
    }

    protected static WebElement getTestElement(String dataTestValue) throws Exception {
        String selector = String.format("[data-test='%s']", dataTestValue);
        return getElement(By.cssSelector(selector));
    }
}