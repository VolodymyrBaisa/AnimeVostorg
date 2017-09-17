package usa.bios.animevostorg.ui.contentscreen.presenter;

import usa.bios.animevostorg.ui.base.BasePresenter;

/**
 * Created by Bios on 8/9/2017.
 */

public interface ContentScreenPresenter extends BasePresenter {
    void fetchingFirstPage();

    void onScrolledRecyclerView(int firstVisibleItemPositions, int visibleItemCount, int totalItemCount);

    void onRefresh();
}