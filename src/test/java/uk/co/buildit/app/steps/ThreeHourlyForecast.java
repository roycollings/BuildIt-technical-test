package uk.co.buildit.app.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.buildit.app.support.data.TestData;

import static uk.co.buildit.app.support.pages.ThreeHourlyForecast.*;

public class ThreeHourlyForecast {
    @When("^I select day ([1-5]) from the 5 day forecast(?: again|)$")
    public void I_select_day_x_from_the_5_day_forecast(int day) throws Exception {
        selectDay(day);
    }

    @Then("^I am shown a 3 hourly forecast for the selected day$")
    public void I_am_shown_a_3_hourly_forecast_for_the_selected_day() throws Exception {
        check3HourlyForecastDisplayed(TestData.getDay());
    }

    @Then("^the 3 hourly forecast is hidden for this day$")
    public void three_hourly_forecasts_is_hidden_for_this_day() throws Exception {
        check3HourlyForecastNotDisplayed(TestData.getDay());
    }

    @Then("^all conditions are correct in the 3 hourly forecast$")
    public void all_conditions_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedConditionsMatchJSON();
    }

    @Then("^all min temperatures are correct in the 3 hourly forecast$")
    public void all_min_temperatures_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedMinTemperaturesMatchJSON();
    }

    @Then("^all max temperatures are correct in the 3 hourly forecast$")
    public void all_max_temperatures_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedMaxTemperaturesMatchJSON();
    }

    @Then("^all wind speeds are correct in the 3 hourly forecast$")
    public void all_wind_speeds_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedWindSpeedsMatchJSON();
    }

    @Then("^all wind directions are correct in the 3 hourly forecast$")
    public void all_wind_directions_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedWindDirectionsMatchJSON();
    }

    @Then("^all rainfalls are correct in the 3 hourly forecast$")
    public void all_rainfalls_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedRainfallsMatchJSON();
    }

    @Then("^all pressures are correct in the 3 hourly forecast$")
    public void all_pressures_are_correct_in_the_3_hourly_forecast() throws Exception {
        checkSelectedPressuresMatchJSON();
    }
}

