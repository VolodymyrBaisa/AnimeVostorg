package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import usa.bios.animevostorg.databinding.ItemPreviewLayoutBinding;
import usa.bios.animevostorg.ui.contentitem.handler.ItemPreviewScreenHandler;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;

/**
 * Created by Bios on 8/16/2017.
 */

public class ContentScreenViewHolder extends RecyclerView.ViewHolder {
    ItemPreviewScreenViewModel itemPreviewScreenViewModel = new ItemPreviewScreenViewModel();

    ContentScreenViewHolder(View itemView) {
        super(itemView);
        ItemPreviewLayoutBinding itemPreviewLayoutBinding = DataBindingUtil.bind(itemView);
        itemPreviewLayoutBinding.setContent(itemPreviewScreenViewModel);

        ItemPreviewScreenHandler itemPreviewScreenHandler = new ItemPreviewScreenHandler();
        itemPreviewLayoutBinding.setHandler(itemPreviewScreenHandler);
    }


}
