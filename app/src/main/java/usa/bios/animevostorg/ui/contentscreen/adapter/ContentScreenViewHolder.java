package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import usa.bios.animevostorg.databinding.ItemContentLayoutBinding;
import usa.bios.animevostorg.ui.contentscreen.viewmodel.ItemContentScreenViewModel;

/**
 * Created by Bios on 8/16/2017.
 */

public class ContentScreenViewHolder extends RecyclerView.ViewHolder {
    ItemContentScreenViewModel itemContentScreenViewModel = new ItemContentScreenViewModel();

    ContentScreenViewHolder(View itemView) {
        super(itemView);
        ItemContentLayoutBinding itemContentLayoutBinding = DataBindingUtil.bind(itemView);
        itemContentLayoutBinding.setContent(itemContentScreenViewModel);
    }
}
