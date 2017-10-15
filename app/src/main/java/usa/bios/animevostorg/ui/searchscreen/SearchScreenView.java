package usa.bios.animevostorg.ui.searchscreen;

import usa.bios.animevostorg.model.DataList;
import usa.bios.animevostorg.ui.base.BaseView;

/**
 * Created by Bios on 9/26/2017.
 */

public interface SearchScreenView extends BaseView {
    void showLoading(int visibility);

    void setRecyclerAdapterItems(DataList dataList);
}
