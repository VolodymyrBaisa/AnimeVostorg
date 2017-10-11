package usa.bios.animevostorg.ui.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

/**
 * Created by Bios on 8/29/2017.
 */

public class ItemPreviewScreenViewModel {
    public ObservableInt contentRating = new ObservableInt(0);
    public ObservableField<String> contentSeriesTotal = new ObservableField<>();
    public ObservableField<String> contentDescription = new ObservableField<>();
    public ObservableField<String> contentTitle = new ObservableField<>();
    public ObservableInt contentSeries = new ObservableInt();
    public ObservableField<String> contentCount = new ObservableField<>();
    public ObservableField<String> contentDirector = new ObservableField<>();
    public ObservableField<String> contentUrlImagePreview = new ObservableField<>();
    public ObservableList<String> contentScreenImage = new ObservableArrayList<>();
    public ObservableField<String> contentYear = new ObservableField<>();
    public ObservableField<String> contentGenre = new ObservableField<>();
    public ObservableField<String> contentType = new ObservableField<>();
    public ObservableInt contentId = new ObservableInt(0);
}
