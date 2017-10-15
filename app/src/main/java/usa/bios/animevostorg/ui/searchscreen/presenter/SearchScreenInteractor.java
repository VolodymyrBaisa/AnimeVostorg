package usa.bios.animevostorg.ui.searchscreen.presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Bios on 9/26/2017.
 */

public interface SearchScreenInteractor {
    Disposable fetchingFilteredData(String gen, String name, String year, String cat);
}
