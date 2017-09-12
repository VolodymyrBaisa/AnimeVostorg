package usa.bios.animevostorg.ui.contentscreen.presenter.impl;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.dao.PageDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.model.Page;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenView;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenInteractor;

/**
 * Created by Bios on 8/13/2017.
 */

public class ContentScreenInteractorImpl implements ContentScreenInteractor {
    private static final String ENDPOINT = "https://api.animevost.org";
    private static final int QUANTITY = 4;

    private ContentScreenView contentScreenView;

    private int previousTotal = 0;
    private boolean loading = true;
    private boolean isConnected = false;

    public ContentScreenInteractorImpl(ContentScreenView contentScreenView) {
        this.contentScreenView = contentScreenView;
    }

    @Override
    public Disposable fetchingData(int page) {
        return APIService.Factory.create(contentScreenView.getCacheDir(), ENDPOINT).getData(page, QUANTITY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dataList -> {
                            isConnected = true;
                            List<Data> datas = dataList.getData();
                            DataDao dataDao = new DataDao();
                            if (page == 1) dataDao.deleteData();
                            setPage(page);
                            datas.forEach(data -> dataDao.storeOrUpdateData(data));
                        },
                        error -> {
                            loading = false;
                            isConnected = false;
                            error.printStackTrace();
                        });
    }

    @Override
    public void onRefresh() {
        fetchingData(1);
    }

    @Override
    public void onScrolledRecyclerView(int firstVisibleItemPositions, int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
            }
        }

        if (!loading && ((visibleItemCount + firstVisibleItemPositions) >= totalItemCount)) {

            if (isConnected) {
                increasePage();
            }
                fetchingData(getPage());
                previousTotal = totalItemCount;
                loading = true;

        }
    }

    @Override
    public int getPage() {
        PageDao pageDao = new PageDao();
        return pageDao.getData().getPage();
    }

    @Override
    public void setPage(int index) {
        PageDao pageDao = new PageDao();
        Page page = new Page();
        page.setPage(index);
        pageDao.storeOrUpdateData(page);
    }

    @Override
    public void increasePage() {
        setPage(getPage() + 1);
    }
}
