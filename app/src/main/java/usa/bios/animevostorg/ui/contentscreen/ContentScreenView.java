package usa.bios.animevostorg.ui.contentscreen;

import java.io.File;

import usa.bios.animevostorg.BaseView;

/**
 * Created by Bios on 8/9/2017.
 */

public interface ContentScreenView extends BaseView, SwipeRefreshView{
    File getCacheDir();
}
