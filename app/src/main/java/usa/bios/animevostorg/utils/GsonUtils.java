package usa.bios.animevostorg.utils;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Bios on 8/30/2017.
 */

public class GsonUtils {
    private GsonUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int getElementCount(String gson) {
        return new Gson().fromJson(gson, HashMap.class).size();
    }
}
