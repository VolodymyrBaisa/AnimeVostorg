package usa.bios.animevostorg.presenters.impl;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.presenters.IBaseView;
import usa.bios.animevostorg.presenters.IContentPresenter;
import usa.bios.animevostorg.presenters.IContentView;

/**
 * Created by Bios on 8/10/2017.
 */

public class ContentPresenter implements IContentPresenter {
    private WeakReference<IContentView> contentViewWeakReference;


    @Override
    public void subscribe(IBaseView iBaseView) {
        contentViewWeakReference = new WeakReference<>((IContentView) iBaseView);
    }

    @Override
    public void unSubscribe() {
        contentViewWeakReference.clear();
    }
}
