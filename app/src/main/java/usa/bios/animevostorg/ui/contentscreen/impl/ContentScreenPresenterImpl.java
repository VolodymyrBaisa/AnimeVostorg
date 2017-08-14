package usa.bios.animevostorg.ui.contentscreen.impl;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.BaseView;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenInteractor;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenPresenter;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenView;
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
        contentScreenInteractor = new ContentScreenInteractorImpl();
    }

    @Override
    public void unSubscribe() {
        if(NullUtils.isNotNull(contentViewWeakReference)) contentViewWeakReference.clear();
        if(NullUtils.isNotNull(contentScreenInteractor)) contentScreenInteractor = null;
    }
}
