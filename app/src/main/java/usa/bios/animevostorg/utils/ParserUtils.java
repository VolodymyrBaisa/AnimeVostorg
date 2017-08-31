package usa.bios.animevostorg.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bios on 8/30/2017.
 */

public class ParserUtils {
    private ParserUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static String getSeriesTotal(String value, String patternString) {
        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
