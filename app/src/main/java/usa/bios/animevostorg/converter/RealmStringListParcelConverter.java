package usa.bios.animevostorg.converter;

import android.databinding.ObservableList;
import android.os.Parcel;

import org.parceler.ParcelConverter;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import usa.bios.animevostorg.model.RealmString;

/**
 * Created by Bios on 10/15/2017.
 */

public class RealmStringListParcelConverter implements ParcelConverter<List<RealmString>> {
    @Override
    public void toParcel(List<RealmString> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(input.size());
            for (RealmString item : input) {
                parcel.writeParcelable(Parcels.wrap(RealmString.class, item), 0);
            }
        }
    }

    @Override
    public List<RealmString> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size < 0) return null;
        List<RealmString> items = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            items.add(Parcels.unwrap(parcel.readParcelable(ObservableList.class.getClassLoader())));
        }
        return items;
    }
}