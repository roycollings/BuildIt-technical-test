package uk.co.buildit.app.support.pages;

import org.openqa.selenium.WebElement;
import uk.co.buildit.app.support.data.TestData;
import uk.co.buildit.app.support.elements.DailyForecast;
import uk.co.buildit.app.support.utilities.CodeHelpers;
import uk.co.buildit.app.support.utilities.DailyWeather;
import uk.co.buildit.app.support.utilities.WeatherDetail;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.co.buildit.app.support.elements.ThreeHourlyForecast.*;

public class ThreeHourlyForecast extends Application {

    public static void selectDay(int day) throws Exception {
        WebElement element = DailyForecast.getDateElement(day);

        element.click();

        TestData.setDay(day);
    }

    public static void check3HourlyForecastDisplayed(int day) throws Exception {
        WebElement detailElement = getHourElement(day, 1);

        // Animation can cause visibility tests to fail.
        CodeHelpers.tryUntilTimeout(
                detailElement::isDisplayed,
                3,
                "Forecast details not visible within 3 seconds"
        );
    }

    public static void check3HourlyForecastNotDisplayed(int day) throws Exception {
        WebElement detailElement = getHourElement(day, 1);

        // Animation can cause visibility tests to fail.
        CodeHelpers.tryUntilTimeout(
                () -> !detailElement.isDisplayed(),
                3,
                "Forecast details still visible within 3 seconds"
        );
    }

    public static void checkSelectedConditionsMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getConditionElement(day, item);

            String expected = detail.getCondition_name();
            String actual = getCondition(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast condition %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedMinTemperaturesMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getMinTemperatureElement(day, item);

            String expected = roundDownToString(detail.getTemp_min());
            String actual = getMinTemperature(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast min temperature %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedMaxTemperaturesMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getMaxTemperatureElement(day, item);

            String expected = roundDownToString(detail.getTemp_max());
            String actual = getMaxTemperature(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast max temperature %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedWindSpeedsMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getWindSpeedElement(day, item);

            String expected = roundDownToString(detail.getWind_speed());
            String actual = getWindSpeed(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast wind speed %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedWindDirectionsMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getWindDirectionElement(day, item);

            String expected = roundDownToString(detail.getWind_direction());
            String actual = getWindDirection(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast wind direction %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedRainfallsMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getRainfallElement(day, item);

            String expected = roundDownToString(detail.getRain());
            String actual = getRainfall(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast rainfall %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }

    public static void checkSelectedPressuresMatchJSON() throws Exception {
        int day = TestData.getDay();
        String city = TestData.getCity();

        DailyWeather dailyWeather = new DailyWeather(city, day);
        List<WeatherDetail> details = dailyWeather.getDetails();

        int item = 0;
        for (WeatherDetail detail : details) {
            item = item + 1;
            WebElement element = getPressureElement(day, item);

            String expected = roundDownToString(detail.getPressure());
            String actual = getPressure(element);

            assertEquals(
                    String.format(
                            "3 hourly forecast pressure %d for day %d is '%s'",
                            item,
                            day,
                            expected
                    ),
                    expected,
                    actual
            );
        }
    }
}
