package usa.bios.animevostorg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Bios on 8/23/2017.
 */

public class DataList implements Parcelable {
    private List<Data> data;

    protected DataList(Parcel in) {
    }

    public static final Creator<DataList> CREATOR = new Creator<DataList>() {
        @Override
        public DataList createFromParcel(Parcel in) {
            return new DataList(in);
        }

        @Override
        public DataList[] newArray(int size) {
            return new DataList[size];
        }
    };

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
