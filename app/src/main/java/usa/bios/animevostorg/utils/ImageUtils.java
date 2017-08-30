package usa.bios.animevostorg.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import usa.bios.animevostorg.R;

/**
 * Created by BIOS on 3/15/2017.
 */

public class ImageUtils {
    private ImageUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static void loadImage(ImageView view, List<String> imageUrls, int imageIndex) {
        if (NullUtils.isNotNull(imageUrls) && !imageUrls.isEmpty()) {
            String url = imageUrls.get(imageIndex);
            Picasso.with(view.getContext())
                    .load(url)
                    .error(R.drawable.no_image)
                    .fit()
                    .into(view);
        }
    }

    public static void loadImage(ImageView view, String imageUrl) {
        if (NullUtils.isNotNull(imageUrl) && !imageUrl.isEmpty()) {
            String url = imageUrl;
            Picasso.with(view.getContext())
                    .load(url)
                    .error(R.drawable.no_image)
                    .fit()
                    .into(view);
        }
    }
}
