package usa.bios.animevostorg.ui.contentscreen;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import usa.bios.animevostorg.R;

/**
 * Created by BIOS on 3/26/2017.
 */

public class ContentScreenRecyclerAdapter extends RecyclerView.Adapter<ContentScreenRecyclerAdapter.ViewHolder> {

    private ContentScreenPresenter contentScreenPresenter;

    public ContentScreenRecyclerAdapter(ContentScreenPresenter contentScreenPresenter) {
        this.contentScreenPresenter = contentScreenPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public CardView root;
        public ImageView cover;
        public TextView numberOfEpisodes;
        public LinearLayout descriptionPanel;
        public HorizontalScrollView titleHorizontalScroll;
        public TextView title;
        public LinearLayout releaseDateLayout;
        public TextView release;
        public LinearLayout genreLayout;
        public HorizontalScrollView genreHorizontalScroll;
        public TextView genre;
        public LinearLayout typeLayout;
        public HorizontalScrollView typeHorizontalScroll;
        public TextView type;
        public LinearLayout episodesLayout;
        public HorizontalScrollView episodesHorizontalScroll;
        public TextView episodes;
        public LinearLayout ratingBarLayout;
        public RatingBar ratingBar;

        ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.itemContentRoot);
            cover = itemView.findViewById(R.id.itemContentCover);
            numberOfEpisodes = itemView.findViewById(R.id.itemContentNumberOfEpisodes);
            descriptionPanel = itemView.findViewById(R.id.itemContentDescriptionPanel);
            titleHorizontalScroll = itemView.findViewById(R.id.itemContentTitleHorizontalScroll);
            title = itemView.findViewById(R.id.itemContentTitle);
            releaseDateLayout = itemView.findViewById(R.id.itemContentReleaseDateLayout);
            release = itemView.findViewById(R.id.itemContentRelease);
            genreLayout = itemView.findViewById(R.id.itemContentGenreLayout);
            genreHorizontalScroll = itemView.findViewById(R.id.itemContentGenreHorizontalScroll);
            genre = itemView.findViewById(R.id.itemContentGenre);
            typeLayout = itemView.findViewById(R.id.itemContentTypeLayout);
            typeHorizontalScroll = itemView.findViewById(R.id.itemContentTypeHorizontalScroll);
            type = itemView.findViewById(R.id.itemContentType);
            episodesLayout = itemView.findViewById(R.id.itemContentEpisodesLayout);
            episodesHorizontalScroll = itemView.findViewById(R.id.itemContentEpisodesHorizontalScroll);
            episodes = itemView.findViewById(R.id.itemContentEpisodes);
            ratingBarLayout = itemView.findViewById(R.id.itemContentRatingBarLayout);
            ratingBar = itemView.findViewById(R.id.itemContentRatingBar);
        }
    }
}
