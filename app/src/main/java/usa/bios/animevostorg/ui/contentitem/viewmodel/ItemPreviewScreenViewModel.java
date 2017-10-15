package usa.bios.animevostorg.ui.contentitem.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;
import java.util.List;

import usa.bios.animevostorg.converter.RealmStringListParcelConverter;
import usa.bios.animevostorg.model.RealmString;

/**
 * Created by Bios on 8/29/2017.
 */

@Parcel
public class ItemPreviewScreenViewModel {
    public ObservableInt contentRating = new ObservableInt(0);
    public ObservableField<String> contentSeriesTotal = new ObservableField<>();
    public ObservableField<String> contentDescription = new ObservableField<>();
    public ObservableField<String> contentTitle = new ObservableField<>();
    public ObservableInt contentSeries = new ObservableInt();
    public ObservableField<String> contentCount = new ObservableField<>();
    public ObservableField<String> contentDirector = new ObservableField<>();
    public ObservableField<String> contentUrlImagePreview = new ObservableField<>();
    @ParcelPropertyConverter(RealmStringListParcelConverter.class)
    public List<RealmString> contentScreenImage = new ArrayList<>();
    public ObservableField<String> contentYear = new ObservableField<>();
    public ObservableField<String> contentGenre = new ObservableField<>();
    public ObservableField<String> contentType = new ObservableField<>();
    public ObservableInt contentId = new ObservableInt(0);
}
