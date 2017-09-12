package usa.bios.animevostorg.utils;

import com.google.gson.Gson;

import usa.bios.animevostorg.HashMap;


/**
 * Created by Bios on 8/30/2017.
 */

public class GsonUtils {
    private GsonUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int getElementCount(String gson) {
        if(gson.isEmpty()) return 0;
        return new Gson().fromJson(gson, HashMap.class).size();
    }
}
