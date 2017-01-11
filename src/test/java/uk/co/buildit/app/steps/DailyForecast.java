package uk.co.buildit.app.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.buildit.app.support.data.TestData;

import static uk.co.buildit.app.support.pages.DailyForecast.*;

public class DailyForecast {

    @When("^I examine the daily weather summary for day ([1-5])$")
    public void I_examine_the_daily_weather_summary_for_day_x(int day) {
        TestData.setDay(day);
    }

    @Then("^the daily forecast shows the current condition$")
    public void the_3_hour_forecast_shows_the_current_condition() throws Exception {
        checkDailyForecastShowsCurrentCondition(TestData.getDay());
    }

    @Then("^the daily forecast shows the current wind speed")
    public void the_3_hour_forecast_shows_the_current_wind_speed() throws Exception {
        checkDailyForecastShowsCurrentWindSpeed(TestData.getDay());
    }

    @Then("^the daily forecast shows the current wind direction$")
    public void the_3_hour_forecast_shows_the_current_wind_direction() throws Exception {
        checkDailyForecastShowsCurrentWindDirection(TestData.getDay());
    }

    @Then("^the daily forecast shows the aggregate rainfall$")
    public void the_3_hour_forecast_shows_the_aggregate_rainfall() throws Exception {
        checkDailyForecastShowsAggregateRainfall(TestData.getDay());
    }

    @Then("^the daily forecast shows the pressure$")
    public void the_3_hour_forecast_shows_the_pressure() throws Exception {
        checkDailyForecastShowsCurrentPressure(TestData.getDay());
    }

    @Then("^the daily forecast shows the daily minimum temperature$")
    public void the_3_hour_forecast_shows_the_daily_minimum_temperature() throws Exception {
        checkDailyForecastShowsMinTemperature(TestData.getDay());
    }

    @Then("^the daily forecast shows the daily maximum temperature$")
    public void the_3_hour_forecast_shows_the_daily_maximum_temperature() throws Exception {
        checkDailyForecastShowsMaxTemperature(TestData.getDay());
    }
}