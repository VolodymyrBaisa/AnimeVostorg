package usa.bios.animevostorg.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Bios on 8/9/2017.
 */

public class TypefaceUtils {
    private TypefaceUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static Typeface font(Context context, String customFontFileNameInAssets) {
        return Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);
    }
}
