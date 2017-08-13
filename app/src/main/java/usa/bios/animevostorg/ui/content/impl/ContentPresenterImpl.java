package usa.bios.animevostorg.ui.content.impl;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.BaseView;
import usa.bios.animevostorg.ui.content.ContentInteractor;
import usa.bios.animevostorg.ui.content.ContentPresenter;
import usa.bios.animevostorg.ui.content.ContentView;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/10/2017.
 */

public class ContentPresenterImpl implements ContentPresenter {
    private WeakReference<ContentView> contentViewWeakReference;
    private ContentInteractor contentInteractor;


    @Override
    public void subscribe(BaseView baseView) {
        contentViewWeakReference = new WeakReference<>((ContentView) baseView);
        contentInteractor = new ContentInteractorImpl();
    }

    @Override
    public void unSubscribe() {
        if(NullUtils.isNotNull(contentViewWeakReference)) contentViewWeakReference.clear();
        if(NullUtils.isNotNull(contentInteractor)) contentInteractor = null;
    }
}
