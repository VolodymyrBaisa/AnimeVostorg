package usa.bios.animevostorg.ui.content;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.BaseView;

/**
 * Created by Bios on 8/10/2017.
 */

public class ContentPresenterImpl implements ContentPresenter {
    private WeakReference<ContentView> contentViewWeakReference;


    @Override
    public void subscribe(BaseView baseView) {
        contentViewWeakReference = new WeakReference<>((ContentView) baseView);
    }

    @Override
    public void unSubscribe() {
        contentViewWeakReference.clear();
    }
}
