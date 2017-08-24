package usa.bios.animevostorg.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Bios on 8/23/2017.
 */

@RealmClass
public class DataList extends RealmObject {
    private RealmList<Data> data = null;

    public List<Data> getData() {
        return data;
    }

    public void setData(RealmList<Data> data) {
        this.data = data;
    }

}
