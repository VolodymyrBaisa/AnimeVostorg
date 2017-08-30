package usa.bios.animevostorg.dao;

import io.realm.Realm;
import io.realm.RealmResults;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/16/2017.
 */

public class DataDao {
    private static final String ID = "realmId";

    public void storeOrUpdateData(Data data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {

            Number number = realm1.where(Data.class).max(ID);
            int nextId;
            if (NullUtils.isNotNull(number)) {
                nextId = number.intValue() + 1;
            } else {
                nextId = 1;
            }

            data.setRealmId(nextId);
            realm1.insertOrUpdate(data);
        });
    }

    public RealmResults<Data> getData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Data.class).findAllAsync();
    }

    public void deleteData() {
        Realm realm = Realm.getDefaultInstance();
        realm.delete(Data.class);
    }
}
