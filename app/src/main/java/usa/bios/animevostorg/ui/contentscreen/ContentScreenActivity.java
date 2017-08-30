package usa.bios.animevostorg.ui.contentscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.File;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.ui.contentscreen.adapter.ContentScreenRecyclerAdapter;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenPresenter;
import usa.bios.animevostorg.ui.contentscreen.presenter.impl.ContentScreenPresenterImpl;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/9/2017.
 */

public class ContentScreenActivity extends AppCompatActivity implements ContentScreenView {
    private static final String FONT = "fonts/roomfer.ttf";
    private ContentScreenPresenter contentScreenPresenter;

    private Toolbar toolbar;
    private TextView toolbarLabel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_screen);

        init();
    }

    private void init() {
        contentScreenPresenter = new ContentScreenPresenterImpl();

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
        if (NullUtils.isNotNull(toolbar)) {
            setSupportActionBar(toolbar);
        }
        setToolbarLabelAndFont();
        setBackButton();
    }

    private void setToolbarLabelAndFont() {
        if (NullUtils.isNotNull(toolbarLabel)) {
            toolbarLabel.setText(R.string.app_name);
            toolbarLabel.setTypeface(TypefaceUtils.font(this, FONT));
        }
    }

    private void setBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (NullUtils.isNotNull(actionBar)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setRecyclerView() {
        if (NullUtils.isNotNull(recyclerView)) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new ContentScreenRecyclerAdapter(new DataDao()));
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        contentScreenPresenter.subscribe(this);
        contentScreenPresenter.getData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contentScreenPresenter.unSubscribe();
    }

    @Override
    public File getCacheDir() {
        return getBaseContext().getCacheDir();
    }
}
