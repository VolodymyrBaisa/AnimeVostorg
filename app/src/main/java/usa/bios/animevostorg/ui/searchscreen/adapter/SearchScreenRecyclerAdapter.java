package usa.bios.animevostorg.ui.searchscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.model.DataList;
import usa.bios.animevostorg.model.RealmString;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;
import usa.bios.animevostorg.utils.CalcUtils;
import usa.bios.animevostorg.utils.GsonUtils;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.ParserUtils;

/**
 * Created by BIOS on 3/26/2017.
 */

public class SearchScreenRecyclerAdapter extends RecyclerView.Adapter<SearchScreenViewHolder> {
    private DataList dataList;
    private static final String SERIES_TOTAL_PATTERN = "\\[(([0-9,-]*||[0-9,A-Z,\\s]*)[а-я,\\s]*[0-9,+]*)\\]";

    public SearchScreenRecyclerAdapter() {
    }

    @Override
    public SearchScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preview_layout, parent, false);
        return new SearchScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchScreenViewHolder holder, int position) {
        if (NullUtils.isNotNull(dataList)) {
            Data data = dataList.getData().get(position);
            ItemPreviewScreenViewModel itemPreviewScreenViewModel = holder.itemPreviewScreenViewModel;

            itemPreviewScreenViewModel.contentRating.set(CalcUtils.calculateRating(data.getRating(), data.getVotes()));
            itemPreviewScreenViewModel.contentSeriesTotal.set(ParserUtils.getSeriesTotal(data.getTitle(), SERIES_TOTAL_PATTERN));
            itemPreviewScreenViewModel.contentDescription.set(data.getDescription());
            itemPreviewScreenViewModel.contentTitle.set(data.getTitle());
            itemPreviewScreenViewModel.contentSeries.set(GsonUtils.getElementCount(data.getSeries()));
            itemPreviewScreenViewModel.contentCount.set(data.getCount());
            itemPreviewScreenViewModel.contentDirector.set(data.getDirector());
            itemPreviewScreenViewModel.contentUrlImagePreview.set(data.getUrlImagePreview());
            itemPreviewScreenViewModel.contentScreenImage.addAll(data.getScreenImage().subList(0, data.getScreenImage().size()));
            itemPreviewScreenViewModel.contentYear.set(data.getYear());
            itemPreviewScreenViewModel.contentGenre.set(data.getGenre());
            itemPreviewScreenViewModel.contentType.set(data.getType());
            itemPreviewScreenViewModel.contentId.set(data.getId());
        }
    }

    @Override
    public void onViewDetachedFromWindow(SearchScreenViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        if (NullUtils.isNotNull(dataList)) {
            return dataList.getData().size();
        }
        return 0;
    }

    public void setItems(DataList dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public DataList geItems(){
        return dataList;
    }
}
