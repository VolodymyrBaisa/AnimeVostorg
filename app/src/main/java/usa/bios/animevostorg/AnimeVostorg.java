package usa.bios.animevostorg;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;

/**
 * Created by Bios on 8/5/2017.
 */

public class AnimeVostorg extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        AnimeVostorg application = (AnimeVostorg) context.getApplicationContext();
        return application.refWatcher;
    }
}
