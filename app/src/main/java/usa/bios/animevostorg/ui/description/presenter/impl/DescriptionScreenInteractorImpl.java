package usa.bios.animevostorg.ui.description.presenter.impl;

import usa.bios.animevostorg.ui.description.DescriptionScreenView;
import usa.bios.animevostorg.ui.description.presenter.DescriptionScreenInteractor;

/**
 * Created by Bios on 10/8/2017.
 */

public class DescriptionScreenInteractorImpl implements DescriptionScreenInteractor {
    private DescriptionScreenView descriptionScreenView;

    public DescriptionScreenInteractorImpl(DescriptionScreenView descriptionScreenView) {
        this.descriptionScreenView = descriptionScreenView;
    }
}
