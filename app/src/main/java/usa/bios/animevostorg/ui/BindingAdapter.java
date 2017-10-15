package usa.bios.animevostorg.ui;

import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import usa.bios.animevostorg.utils.ImageUtils;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/29/2017.
 */

public class BindingAdapter {
    private static final String ENDPOINT = "http://animevost.org";

    @android.databinding.BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
        }
    }

    @android.databinding.BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (NullUtils.isNotNull(imageUrl) && !imageUrl.contains("http")) {
            imageUrl = ENDPOINT.concat(imageUrl);
        }
        ImageUtils.loadImage(view, imageUrl);
    }

    @android.databinding.BindingAdapter({"bind:imageUrls", "imageIndex"})
    public static void loadImage(ImageView view, List<String> imageUrls, int imageIndex) {
        String url = imageUrls.get(imageIndex);

        if (NullUtils.isNotNull(url) && !url.contains("http")) {
            url = ENDPOINT.concat(url);
        }
        ImageUtils.loadImage(view, url);
    }
}
