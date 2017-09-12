package usa.bios.animevostorg;

/**
 * Created by Bios on 9/7/2017.
 */

public class HashMap extends java.util.HashMap {
    @Override
    public Object put(Object key, Object value) {
        if(!containsKey(key)) {
            return super.put(key, value);
        }
        return null;
    }
}
