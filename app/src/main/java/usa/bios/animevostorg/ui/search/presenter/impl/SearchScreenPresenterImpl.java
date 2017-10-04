package usa.bios.animevostorg.ui.search.presenter.impl;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import usa.bios.animevostorg.ui.base.BaseView;
import usa.bios.animevostorg.ui.search.SearchScreenView;
import usa.bios.animevostorg.ui.search.presenter.SearchScreenInteractor;
import usa.bios.animevostorg.ui.search.presenter.SearchScreenPresenter;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 9/26/2017.
 */

public class SearchScreenPresenterImpl implements SearchScreenPresenter {

    private WeakReference<SearchScreenView> searchScreenViewWeakReference;
    private SearchScreenInteractor searchScreenInteractor;


    @Override
    public void subscribe(BaseView baseView) {
        searchScreenViewWeakReference = new WeakReference<>((SearchScreenView) baseView);
        searchScreenInteractor = new SearchScreenInteractorImpl(searchScreenViewWeakReference.get());
    }

    @Override
    public void unSubscribe() {
        if (NullUtils.isNotNull(searchScreenViewWeakReference))
            searchScreenViewWeakReference.clear();
    }

    @Override
    public Disposable fetchingSearchData(String name) {
        return searchScreenInteractor.fetchingFilteredData("", name, "", "");
    }
}
