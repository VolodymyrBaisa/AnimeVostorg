package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.ui.viewmodel.ItemPreviewScreenViewModel;
import usa.bios.animevostorg.utils.CalcUtils;
import usa.bios.animevostorg.utils.GsonUtils;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.ParserUtils;

/**
 * Created by BIOS on 3/26/2017.
 */

public class ContentScreenRecyclerAdapter extends RecyclerView.Adapter<ContentScreenViewHolder> {
    private RealmResults<Data> dataRealmResults;
    private static final String SERIES_TOTAL_PATTERN = "\\[(([0-9,-]*||[0-9,A-Z,\\s]*)[а-я,\\s]*[0-9,+]*)\\]";

    public ContentScreenRecyclerAdapter(DataDao dataDao) {
        dataDao.getData().addChangeListener(datas -> {
            dataRealmResults = datas;
            notifyDataSetChanged();
        });
    }

    @Override
    public ContentScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preview_layout, parent, false);
        return new ContentScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentScreenViewHolder holder, int position) {
        if (NullUtils.isNotNull(dataRealmResults)) {
            Data data = dataRealmResults.get(position);
            ItemPreviewScreenViewModel itemContentScreenViewModel = holder.itemPreviewScreenViewModel;

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
            holder.itemPreviewScreenHandler.setBundleData(data);
        }
    }

    @Override
    public void onViewDetachedFromWindow(ContentScreenViewHolder holder) {
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
