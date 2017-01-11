package uk.co.buildit.app.support.utilities;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.concurrent.Callable;

public class CodeHelpers {
    /**
     * Keeps trying until the action throws no exception and doesn't return false.
     *
     */
    public static <T> T tryUntilTimeout(
            Callable<T> action,
            int timeOutSeconds,
            String failureMessage
    ) throws Exception {
        String exceptionMessage = "";

        long timeOutMillis = System.currentTimeMillis() + (timeOutSeconds * 1000);

        while (timeOutMillis > System.currentTimeMillis()) {
            try {
                T result = action.call();

                try {
                    if (false != (Boolean) result) {
                        return result;
                    }
                } catch (ClassCastException e) {
                    // In this case the result wasn't boolean and didn't fail, so ...
                    return result;
                }
            } catch (Throwable e) {
                exceptionMessage = e.getMessage();
                // Do nothing, just try again.
            }
        }

        if (exceptionMessage.length() > 0) exceptionMessage = " : EXCEPTION MESSAGE = " + exceptionMessage;

        throw new Exception(failureMessage + exceptionMessage);
    }


    /**
     * Expects files to be in the project src/test/resources folder.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getResourceFileContents(String fileName) throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        if (is == null) {
            throw new FileNotFoundException("Could not find resource file " + fileName);
        }

        StringWriter writer = new StringWriter();

        IOUtils.copy(is, writer);
        return writer.toString();
    }
}
