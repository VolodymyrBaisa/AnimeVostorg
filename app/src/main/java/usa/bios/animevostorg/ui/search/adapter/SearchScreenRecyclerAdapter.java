package usa.bios.animevostorg.ui.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.ui.contentscreen.viewmodel.ItemContentScreenViewModel;
import usa.bios.animevostorg.utils.CalcUtils;
import usa.bios.animevostorg.utils.GsonUtils;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.ParserUtils;

/**
 * Created by BIOS on 3/26/2017.
 */

public class SearchScreenRecyclerAdapter extends RecyclerView.Adapter<SearchScreenViewHolder> {
    private RealmResults<Data> dataRealmResults;
    private static final String SERIES_TOTAL_PATTERN = "\\[(([0-9,-]*||[0-9,A-Z,\\s]*)[а-я,\\s]*[0-9,+]*)\\]";

    public SearchScreenRecyclerAdapter(DataDao dataDao) {
        dataDao.getData().addChangeListener(datas -> {
            dataRealmResults = datas;
            notifyDataSetChanged();
        });
    }

    @Override
    public SearchScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SearchScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchScreenViewHolder holder, int position) {
        if (NullUtils.isNotNull(dataRealmResults)) {
            Data data = dataRealmResults.get(position);
            ItemContentScreenViewModel itemContentScreenViewModel = holder.itemSearchScreenViewModel;

            itemContentScreenViewModel.contentRating.set(CalcUtils.calculateRating(data.getRating(), data.getVotes()));
            itemContentScreenViewModel.contentSeriesTotal.set(ParserUtils.getSeriesTotal(data.getTitle(), SERIES_TOTAL_PATTERN));
            itemContentScreenViewModel.contentDescription.set(data.getDescription());
            itemContentScreenViewModel.contentTitle.set(data.getTitle());
            itemContentScreenViewModel.contentSeries.set(GsonUtils.getElementCount(data.getSeries()));
            itemContentScreenViewModel.contentCount.set(data.getCount());
            itemContentScreenViewModel.contentDirector.set(data.getDirector());
            itemContentScreenViewModel.contentUrlImagePreview.set(data.getUrlImagePreview());
            itemContentScreenViewModel.contentYear.set(data.getYear());
            itemContentScreenViewModel.contentGenre.set(data.getGenre());
            itemContentScreenViewModel.contentType.set(data.getType());
            itemContentScreenViewModel.contentId.set(data.getId());
        }
    }

    @Override
    public void onViewDetachedFromWindow(SearchScreenViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        if (NullUtils.isNotNull(dataRealmResults)) {
            return dataRealmResults.size();
        }
        return 0;
    }
}
