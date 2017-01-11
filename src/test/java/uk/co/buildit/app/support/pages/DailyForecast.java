package uk.co.buildit.app.support.pages;

import org.openqa.selenium.WebElement;
import uk.co.buildit.app.support.data.TestData;
import uk.co.buildit.app.support.utilities.DailyWeather;
import uk.co.buildit.app.support.utilities.WeatherDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.co.buildit.app.support.elements.DailyForecast.*;

public class DailyForecast extends Application {
    public static void checkDailyForecastShowsCurrentCondition(int day) throws Exception {
        String expected = getCurrentWeatherFromJSON(day).getCondition_name();
        WebElement dailyConditionElement = getConditionElement(day);
        String actual = getCondition(dailyConditionElement);

        assertEquals(
                "The current condition is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsCurrentPressure(int day) throws Exception {
        BigDecimal jsonValue = getCurrentWeatherFromJSON(day).getPressure();
        String expected = roundDownToString(jsonValue);

        WebElement currentPressureElement = getPressureElement(day);
        String actual = getPressure(currentPressureElement);

        assertEquals(
                "The current pressure is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsCurrentWindSpeed(int day) throws Exception {
        BigDecimal jsonValue = getCurrentWeatherFromJSON(day).getWind_speed();
        String expected = roundDownToString(jsonValue);

        WebElement dailyWindSpeedElement = getWindSpeedElement(day);
        String actual = getWindSpeed(dailyWindSpeedElement);

        assertEquals(
                "The current wind speed is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsCurrentWindDirection(int day) throws Exception {
        BigDecimal jsonValue = getCurrentWeatherFromJSON(day).getWind_direction();
        String expected = roundDownToString(jsonValue);

        WebElement dailyWindDirectionElement = getWindDirectionElement(day);
        String actual = getWindDirection(dailyWindDirectionElement);

        assertEquals(
                "The current wind direction is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsAggregateRainfall(int day) throws Exception {
        BigDecimal expectedAccurate = getAggregateRainfallFromJSON(day);
        String expected = roundDownToString(expectedAccurate);

        String actual = getRainfall(getRainfallElement(day));

        assertEquals(
                "The aggregate rainfall is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsMinTemperature(int day) throws Exception {
        BigDecimal expectedAccurate = getDailyMinTemperature(day);
        String expected = roundDownToString(expectedAccurate);

        String actual = getMinTemperature(getMinTemperatureElement(day));

        assertEquals(
                "The minimum temperature is displayed",
                expected,
                actual
        );
    }

    public static void checkDailyForecastShowsMaxTemperature(int day) throws Exception {
        BigDecimal expectedAccurate = getDailyMaxTemperature(day);
        String expected = roundDownToString(expectedAccurate);

        String actual = getMaxTemperature(getMaxTemperatureElement(day));

        assertEquals(
                "The maximum temperature is displayed",
                expected,
                actual
        );
    }

    private static WeatherDetail getCurrentWeatherFromJSON(int day) throws Exception {
        DailyWeather dailyWeather = new DailyWeather(TestData.getCity(), day);

        // The current weather should match the first detail item in the JSON for this city+day.
        return dailyWeather.getDetails().get(0);
    }

    private static BigDecimal getAggregateRainfallFromJSON(int day) throws Exception {
        DailyWeather dailyWeather = new DailyWeather(TestData.getCity(), day);
        List<WeatherDetail> weatherDetails = dailyWeather.getDetails();

        BigDecimal runningTotal = new BigDecimal(0);

        for (WeatherDetail weatherDetail : weatherDetails) {
            runningTotal = runningTotal.add(weatherDetail.getRain());
        }

        return runningTotal;
    }

    private static BigDecimal getDailyMinTemperature(int day) throws Exception {
        DailyWeather dailyWeather = new DailyWeather(TestData.getCity(), day);
        List<WeatherDetail> weatherDetails = dailyWeather.getDetails();

        BigDecimal mostMinTemperature = new BigDecimal(1000);

        for (WeatherDetail weatherDetail : weatherDetails) {
            BigDecimal minTemperature = weatherDetail.getTemp_min();

            if (minTemperature.compareTo(mostMinTemperature) == -1) {
                mostMinTemperature = minTemperature;
            }
        }

        return mostMinTemperature;
    }

    private static BigDecimal getDailyMaxTemperature(int day) throws Exception {
        DailyWeather dailyWeather = new DailyWeather(TestData.getCity(), day);
        List<WeatherDetail> weatherDetails = dailyWeather.getDetails();

        BigDecimal mostMaxTemperature = new BigDecimal(0);

        for (WeatherDetail weatherDetail : weatherDetails) {
            BigDecimal maxTemperature = weatherDetail.getTemp_max();

            if (maxTemperature.compareTo(mostMaxTemperature) == 1) {
                mostMaxTemperature = maxTemperature;
            }
        }

        return mostMaxTemperature;
    }
}
