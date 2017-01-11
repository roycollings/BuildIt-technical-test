package uk.co.buildit.app.support.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RuntimeSettings {
    private static Logger logger = LogManager.getLogger(RuntimeSettings.class);

    private static Boolean settingsLoaded = false;
    private static Integer findElementTimeoutSeconds;
    private static Integer pageLoadTimeoutSeconds;
    private static String webDriverType;
    private static String applicationUrl;
    private static String phantomJSImage;
    private static String phantomJSPort;
    private static String cucumberOptions;
    private static Map<String, String> customOptions = new HashMap<>();

    public static Integer getFindElementTimeoutSeconds() throws Exception {
        loadRuntimeSettings();
        return findElementTimeoutSeconds;
    }

    public static Integer getPageLOadTimeoutSeconds() throws Exception {
        loadRuntimeSettings();
        return pageLoadTimeoutSeconds;
    }

    public static String getWebDriverType() throws Exception {
        loadRuntimeSettings();
        return webDriverType;
    }

    public static String getApplicationUrl() throws Exception {
        loadRuntimeSettings();
        return applicationUrl;
    }

    public static String getPhantomJSImage() throws Exception {
        loadRuntimeSettings();
        return phantomJSImage;
    }

    public static String getPhantomJSPort() throws Exception {
        loadRuntimeSettings();
        return phantomJSPort;
    }

    public static String getCustomRunTimeSetting(String key) throws Exception {
        loadRuntimeSettings();
        for (Map.Entry<String, String> item : customOptions.entrySet()) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }

        throw new Exception("Custom runtime setting '" + key + "' not found in your pom.xml.");
    }

    public static String getCucumberOptions() throws Exception {
        loadRuntimeSettings();
        return cucumberOptions;
    }

    private static void loadRuntimeSettings() throws Exception {
        if (settingsLoaded) {
            return;
        }

        File temp = new File("target/classes", "test.properties");
        InputStream is = new FileInputStream(temp);
        Properties p = new Properties();
        p.load(is);

        findElementTimeoutSeconds = Integer.valueOf(p.getProperty("runTimeSetting.findElementTimeoutSeconds"));
        pageLoadTimeoutSeconds = Integer.valueOf(p.getProperty("runTimeSetting.pageLoadTimeoutSeconds"));

        webDriverType = (null != System.getProperty("driverType"))
                ? System.getProperty("driverType")
                : p.getProperty("runTimeSetting.defaultDriverType");

        applicationUrl = (null != System.getProperty("website"))
                ? System.getProperty("website")
                : p.getProperty("runTimeSetting.defaultSite");

        cucumberOptions = (null != System.getProperty("cucumber.options"))
                ? System.getProperty("cucumber.options")
                : "";

        phantomJSImage = p.getProperty("runTimeSetting.phantomJSImage");
        phantomJSPort = p.getProperty("runTimeSetting.phantomJSPort");

        loadRuntimeCustomSettings(p);

        logSettings();
        settingsLoaded = true;
    }

    private static void loadRuntimeCustomSettings(Properties p) throws Exception {
        Enumeration e = p.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.matches("^customSetting.*")) {
                customOptions.put(key, p.getProperty(key));
            }
        }
    }

    private static void logSettings() {
        logger.info("Run time setting findElementTimeoutSeconds = " + findElementTimeoutSeconds);
        logger.info("Run time setting pageLoadTimeoutSeconds = " + pageLoadTimeoutSeconds);
        logger.info("Run time setting webDriverType = " + webDriverType);
        logger.info("Run time setting applicationUrl = " + applicationUrl);
        logger.info("Run time setting cucumberOptions = " + cucumberOptions);
        logger.info("Run time setting phantomJSImage = " + phantomJSImage);
        logger.info("Run time setting phantomJSPort = " + phantomJSPort);

        for (Map.Entry<String, String> item : customOptions.entrySet()) {
            logger.info("Custom run time setting " + item.getKey() + " = " + item.getValue());
        }
    }
}
