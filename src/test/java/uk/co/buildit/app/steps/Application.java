package uk.co.buildit.app.steps;

import cucumber.api.java.en.Given;

import static uk.co.buildit.app.support.pages.Application.checkApplicationIsLaunched;
import static uk.co.buildit.app.support.pages.Application.setCurrentCity;

public class Application {
    @Given("^I am viewing the application$")
    public void I_am_viewing_the_application() throws Exception {
        checkApplicationIsLaunched();
        setCurrentCity();
    }
}