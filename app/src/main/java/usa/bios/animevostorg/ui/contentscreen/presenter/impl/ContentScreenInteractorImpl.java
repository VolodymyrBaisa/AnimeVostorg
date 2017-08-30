package usa.bios.animevostorg.ui.contentscreen.presenter.impl;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenView;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenInteractor;

/**
 * Created by Bios on 8/13/2017.
 */

public class ContentScreenInteractorImpl implements ContentScreenInteractor {
    private static final String ENDPOINT = "https://api.animevost.org";
    private static final int QUANTITY = 20;

    private ContentScreenView contentScreenView;

    public ContentScreenInteractorImpl(ContentScreenView contentScreenView) {
        this.contentScreenView = contentScreenView;
    }

    @Override
    public Disposable fetchingData() {
        return APIService.Factory.create(contentScreenView.getCacheDir(), ENDPOINT).getData(1, QUANTITY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dataList -> {
                            List<Data> datas = dataList.getData();
                            DataDao dataDao = new DataDao();

                            datas.forEach(data -> dataDao.storeOrUpdateData(data));
                        },
                        error -> {
                            error.printStackTrace();
                        });
    }
}
