package usa.bios.animevostorg.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import usa.bios.animevostorg.R;

/**
 * Created by Bios on 8/30/2017.
 */

public class CalcUtils {
    private CalcUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int calculateRating(double rating, double votes) {
        return (int) Math.round(rating / votes);
    }

    public static int calculateNoOfColumns(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float widthDisplay = displayMetrics.widthPixels;
        int itemWidth = (int) context.getResources().getDimension(R.dimen.content_screen_card_width);
        return (int) widthDisplay / itemWidth;
    }
}
