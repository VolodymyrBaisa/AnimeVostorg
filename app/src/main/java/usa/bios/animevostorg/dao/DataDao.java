package usa.bios.animevostorg.dao;

import java.util.List;

import io.realm.Realm;
import usa.bios.animevostorg.model.DataList;

/**
 * Created by Bios on 8/16/2017.
 */

public class DataDao {
    public void storeOrUpdateData(DataList data) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(data));
    }

    public List<DataList> getData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(DataList.class).findAll();
    }

    public void deleteData() {
        Realm realm = Realm.getDefaultInstance();
        realm.delete(DataList.class);
    }
}
