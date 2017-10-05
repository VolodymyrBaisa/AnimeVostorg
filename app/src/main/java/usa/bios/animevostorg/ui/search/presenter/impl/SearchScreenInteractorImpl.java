package usa.bios.animevostorg.ui.search.presenter.impl;

import android.view.View;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.BuildConfig;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.search.SearchScreenView;
import usa.bios.animevostorg.ui.search.presenter.SearchScreenInteractor;

/**
 * Created by Bios on 9/26/2017.
 */

public class SearchScreenInteractorImpl implements SearchScreenInteractor {
    private SearchScreenView searchScreenView;

    public SearchScreenInteractorImpl(SearchScreenView searchScreenView) {
        this.searchScreenView = searchScreenView;
    }

    @Override
    public Disposable fetchingFilteredData(String gen, String name, String year, String cat) {
        searchScreenView.showLoading(View.VISIBLE);
        return APIService.Factory.create(searchScreenView.getCacheDir(), BuildConfig.SERVER_API_URL).getFilteredData(gen, name, year, cat).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dataList -> {
                            searchScreenView.showLoading(View.GONE);
                        },
                        error -> {
                            if (error instanceof HttpException) {
                                searchScreenView.onHttpError(R.string.connection_error, ((HttpException) error).code());
                            } else {
                                searchScreenView.onError(R.string.internet_connection_error);
                            }
                            searchScreenView.showLoading(View.GONE);
                        });
    }
}
