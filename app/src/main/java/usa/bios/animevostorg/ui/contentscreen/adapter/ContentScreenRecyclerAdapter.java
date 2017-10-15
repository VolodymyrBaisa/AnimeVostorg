package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.model.RealmString;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;
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
