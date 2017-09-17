package usa.bios.animevostorg.ui.contentscreen.presenter.impl;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.ui.base.BaseView;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenView;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenInteractor;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenPresenter;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/10/2017.
 */

public class ContentScreenPresenterImpl implements ContentScreenPresenter {
    private WeakReference<ContentScreenView> contentViewWeakReference;
    private ContentScreenInteractor contentScreenInteractor;


    @Override
    public void subscribe(BaseView baseView) {
        contentViewWeakReference = new WeakReference<>((ContentScreenView) baseView);
        contentScreenInteractor = new ContentScreenInteractorImpl(contentViewWeakReference.get());
    }

    @Override
    public void unSubscribe() {
        if (NullUtils.isNotNull(contentViewWeakReference)) contentViewWeakReference.clear();
        if (NullUtils.isNotNull(contentScreenInteractor)) contentScreenInteractor = null;
    }

    @Override
    public void fetchingFirstPage() {
        contentScreenInteractor.fetchingData(1);
    }

    @Override
    public void onScrolledRecyclerView(int firstVisibleItemPositions, int visibleItemCount, int totalItemCount) {
        contentScreenInteractor.onScrolledRecyclerView(firstVisibleItemPositions, visibleItemCount, totalItemCount);
    }

    @Override
    public void onRefresh() {
        contentScreenInteractor.fetchingData(1);
    }
}
