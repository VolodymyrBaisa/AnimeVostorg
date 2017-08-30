package usa.bios.animevostorg.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Bios on 8/5/2017.
 */

public class SplashScreen extends RealmObject {
    @PrimaryKey
    private int realmId;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
