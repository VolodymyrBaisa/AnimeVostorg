package usa.bios.animevostorg.presenters;

/**
 * Created by Bios on 8/5/2017.
 */

public interface IBasePresenter {
    void subscribe(IBaseView iBaseView);
    void unSubscribe();
}
