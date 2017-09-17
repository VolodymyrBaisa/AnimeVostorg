package usa.bios.animevostorg.ui.contentscreen.presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Bios on 8/13/2017.
 */

public interface ContentScreenInteractor {
    Disposable fetchingData(int page);

    void onScrolledRecyclerView(int firstVisibleItemPositions, int visibleItemCount, int totalItemCount);
}
