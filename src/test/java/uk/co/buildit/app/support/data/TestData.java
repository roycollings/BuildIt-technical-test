package uk.co.buildit.app.support.data;

public class TestData {
    private static String city = "";
    private static int day = 0;

    public static void setCity(String value) {
        city = value;
    }

    public static String getCity() {
        return city;
    }

    public static void setDay(int value) {
        day = value;
    }

    public static int getDay() {
        return day;
    }

    public static void reset() {
        city = "";
        day = 0;
    }
}
