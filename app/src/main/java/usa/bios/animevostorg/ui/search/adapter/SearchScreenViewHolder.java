package usa.bios.animevostorg.ui.search.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import usa.bios.animevostorg.databinding.ItemPreviewLayoutBinding;
import usa.bios.animevostorg.ui.viewmodel.ItemPreviewScreenViewModel;

/**
 * Created by Bios on 8/16/2017.
 */

public class SearchScreenViewHolder extends RecyclerView.ViewHolder {
    ItemPreviewScreenViewModel itemPreviewScreenViewModel = new ItemPreviewScreenViewModel();

    SearchScreenViewHolder(View itemView) {
        super(itemView);
        ItemPreviewLayoutBinding itemPreviewLayoutBinding = DataBindingUtil.bind(itemView);
        itemPreviewLayoutBinding.setContent(itemPreviewScreenViewModel);
    }
}
