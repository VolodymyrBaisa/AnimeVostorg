package usa.bios.animevostorg.helpers;

/**
 * Created by Bios on 8/8/2017.
 */

public class NullHelper {
    private NullHelper() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isNull(Object object){
        return object == null;
    }
}
