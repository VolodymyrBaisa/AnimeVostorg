package usa.bios.animevostorg;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import usa.bios.animevostorg.dao.DataDao;

/**
 * Created by Bios on 8/5/2017.
 */

public class AnimeVostorg extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        setRealmConfiguration();

        refWatcher = LeakCanary.install(this);
    }

    private void setRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("avdb")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RefWatcher getRefWatcher(Context context) {
        AnimeVostorg application = (AnimeVostorg) context.getApplicationContext();
        return application.refWatcher;
    }
}
