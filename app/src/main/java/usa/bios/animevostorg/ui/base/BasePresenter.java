package usa.bios.animevostorg.ui.base;

/**
 * Created by Bios on 8/5/2017.
 */

public interface BasePresenter {
    void subscribe(BaseView baseView);

    void unSubscribe();
}
