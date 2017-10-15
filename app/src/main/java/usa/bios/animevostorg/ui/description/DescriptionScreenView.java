package usa.bios.animevostorg.ui.description;

import usa.bios.animevostorg.ui.base.BaseView;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;

/**
 * Created by Bios on 10/7/2017.
 */

public interface DescriptionScreenView extends BaseView {
    ItemPreviewScreenViewModel getItems();

    void setImagePreview(String url);
}
