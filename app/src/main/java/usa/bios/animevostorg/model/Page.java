package usa.bios.animevostorg.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Bios on 8/31/2017.
 */

@RealmClass
public class Page extends RealmObject {
    @PrimaryKey
    private int id;

    private int page = 1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
