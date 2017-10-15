package usa.bios.animevostorg.ui.searchscreen.presenter;

import io.reactivex.disposables.Disposable;
import usa.bios.animevostorg.ui.base.BasePresenter;

/**
 * Created by Bios on 9/26/2017.
 */

public interface SearchScreenPresenter extends BasePresenter {
    Disposable fetchingSearchData(String name);
}
