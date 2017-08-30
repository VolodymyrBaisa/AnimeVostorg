package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.HashMap;

import io.realm.RealmResults;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.ui.contentscreen.viewmodel.ItemContentScreenViewModel;
import usa.bios.animevostorg.utils.GsonUtils;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by BIOS on 3/26/2017.
 */

public class ContentScreenRecyclerAdapter extends RecyclerView.Adapter<ContentScreenViewHolder> {
    private RealmResults<Data> dataRealmResults;

    public ContentScreenRecyclerAdapter(DataDao dataDao) {
        dataDao.getData().addChangeListener(datas -> {
            dataRealmResults = datas;
            notifyDataSetChanged();
        });
    }

    @Override
    public ContentScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_layout, parent, false);
        return new ContentScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentScreenViewHolder holder, int position) {
        if (NullUtils.isNotNull(dataRealmResults)) {
            Data data = dataRealmResults.get(position);
            ItemContentScreenViewModel itemContentScreenViewModel = holder.itemContentScreenViewModel;

            itemContentScreenViewModel.contentRating.set(data.getRating());
            itemContentScreenViewModel.contentVotes.set(data.getVotes());
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
