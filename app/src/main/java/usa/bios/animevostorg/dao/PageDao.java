package usa.bios.animevostorg.dao;

import io.realm.Realm;
import usa.bios.animevostorg.model.Page;

/**
 * Created by Bios on 8/31/2017.
 */

public class PageDao {
        public Page getData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Page.class).findFirst();
    }

    public void storeOrUpdateData(Page page) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.insertOrUpdate(page);
        });
    }
}
