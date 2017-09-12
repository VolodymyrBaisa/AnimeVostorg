package usa.bios.animevostorg.dao;

import io.realm.Realm;
import io.realm.RealmResults;
import usa.bios.animevostorg.model.Data;

/**
 * Created by Bios on 8/16/2017.
 */

public class DataDao {
    public RealmResults<Data> getData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Data.class).findAllAsync();
    }

    public void storeOrUpdateData(Data data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.insertOrUpdate(data);
        });
    }

    public void deleteData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
                realm1.delete(Data.class)
        );
    }
}
