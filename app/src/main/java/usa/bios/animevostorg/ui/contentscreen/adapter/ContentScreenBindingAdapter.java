package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import usa.bios.animevostorg.utils.ImageUtils;

/**
 * Created by Bios on 8/29/2017.
 */

public class ContentScreenBindingAdapter {
    private static final String ENDPOINT = "http://animevost.org";

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(!imageUrl.contains("http")){
            imageUrl = ENDPOINT.concat(imageUrl);
        }
        ImageUtils.loadImage(view, imageUrl);
    }

    @BindingAdapter({"bind:imageUrls", "imageIndex"})
    public static void loadImage(ImageView view, List<String> imageUrls, int imageIndex) {
        String url = imageUrls.get(imageIndex);

        if(!url.contains("http")){
            url = ENDPOINT.concat(url);
        }
        ImageUtils.loadImage(view, url);
    }
}