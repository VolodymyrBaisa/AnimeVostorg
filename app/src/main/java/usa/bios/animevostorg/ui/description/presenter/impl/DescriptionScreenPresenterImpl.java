package usa.bios.animevostorg.ui.description.presenter.impl;

import java.lang.ref.WeakReference;

import usa.bios.animevostorg.ui.base.BaseView;
import usa.bios.animevostorg.ui.description.DescriptionScreenView;
import usa.bios.animevostorg.ui.description.presenter.DescriptionScreenInteractor;
import usa.bios.animevostorg.ui.description.presenter.DescriptionScreenPresenter;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 10/8/2017.
 */

public class DescriptionScreenPresenterImpl implements DescriptionScreenPresenter {
    private WeakReference<DescriptionScreenView> descriptionScreenViewWeakReference;
    private DescriptionScreenInteractor descriptionScreenInteractor;

    @Override
    public void subscribe(BaseView baseView) {
        descriptionScreenViewWeakReference = new WeakReference<>((DescriptionScreenView) baseView);
        descriptionScreenInteractor = new DescriptionScreenInteractorImpl(descriptionScreenViewWeakReference.get());
    }

    @Override
    public void unSubscribe() {
        if (NullUtils.isNotNull(descriptionScreenViewWeakReference)) descriptionScreenViewWeakReference.clear();
    }
}
