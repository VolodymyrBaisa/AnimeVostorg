package usa.bios.animevostorg.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Bios on 8/23/2017.
 */

@Parcel
public class DataList {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
