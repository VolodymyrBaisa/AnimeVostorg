package usa.bios.animevostorg.ui.contentscreen.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenPresenter;

/**
 * Created by Bios on 8/16/2017.
 */

public class ContentScreenViewHolder extends RecyclerView.ViewHolder {
    private ContentScreenPresenter contentScreenPresenter;

    private CardView root;
    private ImageView cover;
    private TextView numberOfEpisodes;
    private LinearLayout descriptionPanel;
    private HorizontalScrollView titleHorizontalScroll;
    private TextView title;
    private LinearLayout releaseDateLayout;
    private TextView release;
    private LinearLayout genreLayout;
    private HorizontalScrollView genreHorizontalScroll;
    private TextView genre;
    private LinearLayout typeLayout;
    private HorizontalScrollView typeHorizontalScroll;
    private TextView type;
    private LinearLayout episodesLayout;
    private HorizontalScrollView episodesHorizontalScroll;
    private TextView episodes;
    private LinearLayout ratingBarLayout;
    private RatingBar ratingBar;

    ContentScreenViewHolder(View itemView, ContentScreenPresenter contentScreenPresenter) {
        super(itemView);
        this.contentScreenPresenter = contentScreenPresenter;

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
