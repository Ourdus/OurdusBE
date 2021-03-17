package ourdus.ourdusspring.util;

public class StringUtils {
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty() || string.equals("");
    }
}
