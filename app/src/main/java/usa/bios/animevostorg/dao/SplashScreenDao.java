package usa.bios.animevostorg.dao;

import io.realm.Realm;
import usa.bios.animevostorg.model.SplashScreen;

/**
 * Created by Bios on 8/12/2017.
 */

public class SplashScreenDao {

    public void storeOrUpdateSplashScreen(SplashScreen splashScreen) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(splashScreen));
    }

    public SplashScreen getSplashScreen() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(SplashScreen.class).findFirst();
    }
}
