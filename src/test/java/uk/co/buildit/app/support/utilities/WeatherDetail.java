package uk.co.buildit.app.support.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class WeatherDetail {
    private String jsonString;
    private JSONObject day;

    private BigDecimal temp_min;
    private BigDecimal temp_max;
    private String condition_name;
    private String condition_description;
    private BigDecimal wind_speed;
    private BigDecimal wind_direction;
    private BigDecimal rain;
    private BigDecimal pressure;
    private String detailDate;

    public WeatherDetail(String jsonString, int detailNumber) throws Exception {
        this.jsonString = jsonString;

        getDay(detailNumber);
        getDataForThisDay();
    }

    public BigDecimal getTemp_min() {
        return temp_min;
    }

    public BigDecimal getTemp_max() {
        return temp_max;
    }

    public String getCondition_name() {
        return condition_name;
    }

    public String getCondition_description() {
        return condition_description;
    }

    public BigDecimal getWind_speed() {
        return wind_speed;
    }

    public BigDecimal getWind_direction() {
        return wind_direction;
    }

    public BigDecimal getRain() {
        return rain;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public String getDetailDate() {
        return detailDate;
    }

    private void getDay(int number) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray daysArray = jsonObject.getJSONArray("list");

        day = (JSONObject) daysArray.get(number);
    }

    private void getDataForThisDay() {
        getMainData();
        getWeatherData();
        getWindData();
        getRainData();
        getDate();
    }

    private void getMainData() {
        JSONObject main = day.getJSONObject("main");
        temp_min = main.getBigDecimal("temp_min");
        temp_max = main.getBigDecimal("temp_max");
        pressure = main.getBigDecimal("pressure");
    }

    private void getWeatherData() {
        JSONObject weather = day.getJSONArray("weather").getJSONObject(0);
        condition_name = weather.getString("main");
        condition_description = weather.getString("description");
    }

    private void getWindData() {
        JSONObject wind = day.getJSONObject("wind");
        wind_speed = wind.getBigDecimal("speed");
        wind_direction = wind.getBigDecimal("deg");
    }

    private void getRainData() {
        JSONObject rain = day.getJSONObject("rain");

        try {
            this.rain = rain.getBigDecimal("3h");
        } catch (JSONException e) {
            this.rain = (BigDecimal.ZERO);
        }
    }

    private void getDate() {
        detailDate = day.getString("dt_txt");
    }
}
