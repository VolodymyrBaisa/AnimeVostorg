package usa.bios.animevostorg.ui.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.content.impl.ContentPresenterImpl;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/9/2017.
 */

public class ContentActivity extends AppCompatActivity implements ContentView {
    private ContentPresenter contentPresenter;

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
        contentPresenter = new ContentPresenterImpl();

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

        if (NullUtils.isNotNull(toolbarLabel)) {
            toolbarLabel.setText(R.string.app_name);
            String font = "fonts/roomfer.ttf";
            toolbarLabel.setTypeface(TypefaceUtils.font(this, font));
        }

        ActionBar actionBar = getSupportActionBar();
        if (NullUtils.isNotNull(actionBar)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setRecyclerView() {
        if (NullUtils.isNotNull(recyclerView)) {
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
