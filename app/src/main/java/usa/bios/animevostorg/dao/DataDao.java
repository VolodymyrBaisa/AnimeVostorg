package usa.bios.animevostorg.dao;

import java.util.List;

import io.realm.Realm;
import usa.bios.animevostorg.model.RealmDataList;

/**
 * Created by Bios on 8/16/2017.
 */

public class DataDao {
    public void storeOrUpdateData(RealmDataList data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(data));
    }

    public List<RealmDataList> getData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmDataList.class).findAll();
    }

    public void deleteData() {
        Realm realm = Realm.getDefaultInstance();
        realm.delete(RealmDataList.class);
    }
}
