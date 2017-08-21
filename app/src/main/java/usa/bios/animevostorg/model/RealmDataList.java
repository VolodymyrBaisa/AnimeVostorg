package usa.bios.animevostorg.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Bios on 8/16/2017.
 */

public class RealmDataList extends RealmObject {
    private RealmList<Data> data = new RealmList<>();
}
