package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenPresenter;

/**
 * Created by BIOS on 3/26/2017.
 */

public class ContentScreenRecyclerAdapter extends RecyclerView.Adapter<ContentScreenViewHolder> {

    private ContentScreenPresenter contentScreenPresenter;

    public ContentScreenRecyclerAdapter(ContentScreenPresenter contentScreenPresenter) {
        this.contentScreenPresenter = contentScreenPresenter;
    }

    @Override
    public ContentScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_layout, parent, false);
        return new ContentScreenViewHolder(view, contentScreenPresenter);
    }

    @Override
    public void onBindViewHolder(ContentScreenViewHolder holder, int position) {

    }

    @Override
    public void onViewDetachedFromWindow(ContentScreenViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
