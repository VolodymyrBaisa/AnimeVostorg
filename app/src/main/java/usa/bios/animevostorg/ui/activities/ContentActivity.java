package usa.bios.animevostorg.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.SplashScreenDao;
import usa.bios.animevostorg.helpers.NullHelper;
import usa.bios.animevostorg.helpers.TypefaceHelper;
import usa.bios.animevostorg.presenters.IContentPresenter;
import usa.bios.animevostorg.presenters.IContentView;
import usa.bios.animevostorg.presenters.impl.ContentPresenter;
import usa.bios.animevostorg.ui.adapters.ContentRecyclerAdapter;

/**
 * Created by Bios on 8/9/2017.
 */

public class ContentActivity extends AppCompatActivity implements IContentView {
    private IContentPresenter contentPresenter;

    private Toolbar toolbar;
    private TextView toolbarLabel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        init();
    }

    private void init() {
        contentPresenter = new ContentPresenter();

        toolbar = (Toolbar) findViewById(R.id.toolbarLayout);
        toolbarLabel = (TextView) findViewById(R.id.toolbarLabel);
        recyclerView = (RecyclerView) findViewById(R.id.contentRecyclerContainer);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setToolbar();
        setRecyclerView();
    }

    private void setToolbar() {
        if (NullHelper.isNotNull(toolbar)) {
            setSupportActionBar(toolbar);
        }

        if (NullHelper.isNotNull(toolbarLabel)) {
            toolbarLabel.setText(R.string.app_name);
            String font = "fonts/roomfer.ttf";
            toolbarLabel.setTypeface(TypefaceHelper.font(this, font));
        }

        ActionBar actionBar = getSupportActionBar();
        if (NullHelper.isNotNull(actionBar)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setRecyclerView() {
        if (NullHelper.isNotNull(recyclerView)) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new ContentRecyclerAdapter());
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        contentPresenter.subscribe(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        contentPresenter.unSubscribe();
    }


}
