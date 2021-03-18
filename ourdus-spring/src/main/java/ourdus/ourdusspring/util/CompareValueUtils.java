package ourdus.ourdusspring.util;

public class CompareValueUtils {
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty() || string.equals("");
    }

    public static <T extends Object> T changeValue(T before, T after) {
        if (after == null || after.equals("")) {
            return before;
        }
        return after;
    }
}
