package usa.bios.animevostorg.helpers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Bios on 8/9/2017.
 */

public class TypefaceHelper {
    private TypefaceHelper() {
        throw new IllegalAccessError("Utility class");
    }

    public static Typeface font(Context context, String customFontFileNameInAssets) {
        return Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);
    }
}
