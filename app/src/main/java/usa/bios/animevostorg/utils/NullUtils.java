package usa.bios.animevostorg.utils;

/**
 * Created by Bios on 8/8/2017.
 */

public class NullUtils {
    private NullUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }
}
