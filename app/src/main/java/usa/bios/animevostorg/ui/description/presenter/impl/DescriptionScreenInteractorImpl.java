package usa.bios.animevostorg.ui.description.presenter.impl;

import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;
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

    @Override
    public void fillPage() {
        ItemPreviewScreenViewModel items = descriptionScreenView.getItems();
        if(items.contentScreenImage.size() > 0)
        descriptionScreenView.setImagePreview(items.contentScreenImage.get(2).value);
    }
}
