package usa.bios.animevostorg.utils;

/**
 * Created by Bios on 8/30/2017.
 */

public class CalcUtils {
    private CalcUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int rating(double rating, double votes) {
        return (int) Math.round(rating / votes);
    }
}
