package usa.bios.animevostorg.ui.contentscreen.presenter.impl;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.BuildConfig;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.dao.PageDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.model.DataList;
import usa.bios.animevostorg.model.Page;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenView;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenInteractor;

/**
 * Created by Bios on 8/13/2017.
 */

public class ContentScreenInteractorImpl implements ContentScreenInteractor {
    private static final int QUANTITY = 20;
    private static final int HIDE_ITEM = 5;

    private ContentScreenView contentScreenView;

    private int previousTotal = 0;
    private boolean loading = true;

    public ContentScreenInteractorImpl(ContentScreenView contentScreenView) {
        this.contentScreenView = contentScreenView;
    }

    @Override
    public Disposable fetchingData(int page) {
        return APIService.Factory.create(contentScreenView.getCacheDir(), BuildConfig.SERVER_API_URL).getData(page, QUANTITY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dataList -> {
                            DataDao dataDao = new DataDao();
                            if (page == 1) dataDao.deleteData();
                            setPage(page);

                            saveData(dataList, dataDao);

                            contentScreenView.setSwipeRefreshing(false);
                        },
                        error -> {
                            if (error instanceof HttpException) {
                                contentScreenView.onHttpError(R.string.connection_error, ((HttpException) error).code());
                            }

                            contentScreenView.setSwipeRefreshing(true);
                            loading = false;
                        });
    }

    private void saveData(DataList dataList, DataDao dataDao) {
        List<Data> datas = dataList.getData();
        datas.forEach(data -> dataDao.storeOrUpdateData(data));
    }

    @Override
    public void onScrolledRecyclerView(int firstVisibleItemPositions, int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
            }
        }

        if (!loading && ((visibleItemCount + firstVisibleItemPositions + HIDE_ITEM) >= totalItemCount)) {
            if(contentScreenView.isNetworkConnected()){
                increasePage();
            }
            fetchingData(getPage());

            previousTotal = totalItemCount;
            loading = true;
        }
    }

    private int getPage() {
        PageDao pageDao = new PageDao();
        return pageDao.getData().getPage();
    }

    private void setPage(int index) {
        PageDao pageDao = new PageDao();
        Page page = new Page();
        page.setPage(index);
        pageDao.storeOrUpdateData(page);
    }

    private void increasePage() {
        setPage(getPage() + 1);
    }
}
