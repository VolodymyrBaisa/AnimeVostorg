package usa.bios.animevostorg.model;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Bios on 8/16/2017.
 */
@Parcel
@RealmClass
public class RealmString extends RealmObject {
    public String value;
}
