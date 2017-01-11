package uk.co.buildit.app.support.utilities;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DailyWeather {
    private String jsonString;
    private List<WeatherDetail> allDetails;
    private List<String> allDays;

    private List<WeatherDetail> details;

    public DailyWeather(String cityName, int day) throws Exception {
        setJsonString(cityName);
        setDetails(day);
    }

    public List<WeatherDetail> getDetails() {
        return details;
    }

    private void setJsonString(String cityName) throws Exception {
        String fileName = String.format("data/%s.json", cityName.toLowerCase());

        jsonString = CodeHelpers.getResourceFileContents(fileName);
    }

    private void setDetails(int day) throws Exception {
        setAllDetails();
        setAllDays();

        String dayString = allDays.get(day - 1);

        details = new ArrayList<>();

        for (WeatherDetail detail : allDetails) {
            String detailDate = detail.getDetailDate();

            if (detailDate.startsWith(dayString)) {
                details.add(detail);
            }
        }
    }

    private void setAllDays() {
        allDays = new ArrayList<>();

        for (WeatherDetail detail : allDetails) {
            String day = detail.getDetailDate();
            day = day.replaceAll(" .+$", "");

            if (!allDays.contains(day)) {
                allDays.add(day);
            }
        }
    }

    private void setAllDetails() throws Exception {
        allDetails = new ArrayList<>();
        for (int item = 0; item <= 100; item++) {
            try {
                allDetails.add(new WeatherDetail(jsonString, item));
            } catch (JSONException e) {
                break;
            }
        }
    }
}
