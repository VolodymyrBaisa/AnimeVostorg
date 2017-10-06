package usa.bios.animevostorg.ui.search.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import usa.bios.animevostorg.databinding.ItemLayoutBinding;
import usa.bios.animevostorg.ui.contentscreen.viewmodel.ItemContentScreenViewModel;

/**
 * Created by Bios on 8/16/2017.
 */

public class SearchScreenViewHolder extends RecyclerView.ViewHolder {
    ItemContentScreenViewModel itemSearchScreenViewModel = new ItemContentScreenViewModel();

    SearchScreenViewHolder(View itemView) {
        super(itemView);
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.bind(itemView);
        itemLayoutBinding.setContent(itemSearchScreenViewModel);
    }
}
