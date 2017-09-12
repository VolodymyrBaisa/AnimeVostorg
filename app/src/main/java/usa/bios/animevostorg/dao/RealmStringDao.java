package usa.bios.animevostorg.dao;

import io.realm.Realm;
import usa.bios.animevostorg.model.RealmString;

/**
 * Created by Bios on 9/7/2017.
 */

public class RealmStringDao {

    public void deleteData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 ->
                realm1.delete(RealmString.class)
        );
    }
}
