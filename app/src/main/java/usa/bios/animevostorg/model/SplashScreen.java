package usa.bios.animevostorg.model;

import io.realm.RealmObject;

/**
 * Created by Bios on 8/5/2017.
 */

public class SplashScreen extends RealmObject {
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
