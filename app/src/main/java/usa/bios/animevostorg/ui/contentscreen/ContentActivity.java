package usa.bios.animevostorg.ui.contentscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.contentscreen.adapter.ContentScreenRecyclerAdapter;
import usa.bios.animevostorg.ui.contentscreen.presenter.ContentScreenPresenter;
import usa.bios.animevostorg.ui.contentscreen.presenter.impl.ContentScreenPresenterImpl;
import usa.bios.animevostorg.ui.searchscreen.SearchActivity;
import usa.bios.animevostorg.utils.CalcUtils;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/9/2017.
 */

public class ContentActivity extends BaseActivity implements ContentScreenView {
    private static final String FONT = "fonts/roomfer.ttf";
    private static final String RECYCLER_ITEM_POSITION = "content_recycler_item_position";

    private ContentScreenPresenter contentScreenPresenter;

    private Toolbar toolbar;
    private TextView toolbarLabel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton floatingActionButton;
    private int recyclerScrollToPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_screen);

        init();
    }

    private void init() {
        contentScreenPresenter = new ContentScreenPresenterImpl();

        toolbar = (Toolbar) findViewById(R.id.contentToolbarLayout);
        toolbarLabel = (TextView) findViewById(R.id.toolbarLabel);
        recyclerView = (RecyclerView) findViewById(R.id.contentRecyclerContainer);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.contentSwipeRefreshLayout);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.contentFab);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (NullUtils.isNull(savedInstanceState)) {
            contentScreenPresenter.fetchingFirstPage();
        }

        setToolbar();
        setRecyclerView();
        setSwipeRefreshLayout();
        setFab();
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
            recyclerView.setLayoutManager(new GridLayoutManager(this, CalcUtils.calculateNoOfColumns(this)));
            recyclerView.scrollToPosition(recyclerScrollToPosition);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    scrolledRecyclerView(recyclerView);
                }
            });
        }
    }

    private void scrolledRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = ((GridLayoutManager) recyclerView.getLayoutManager());

        int firstVisibleItemPositions = gridLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = gridLayoutManager.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
        contentScreenPresenter.onScrolledRecyclerView(firstVisibleItemPositions, visibleItemCount, totalItemCount);
    }

    private void setSwipeRefreshLayout() {
        if (NullUtils.isNotNull(swipeRefreshLayout)) {
            swipeRefreshLayout.setOnRefreshListener(() -> contentScreenPresenter.onRefresh());
            swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        }
    }

    @Override
    public void setSwipeRefreshing(boolean refreshing) {
        if (NullUtils.isNotNull(swipeRefreshLayout)) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    private void setFab() {
        if (NullUtils.isNotNull(floatingActionButton)) {
            floatingActionButton.setOnClickListener(view -> {
                if (NullUtils.isNotNull(recyclerView)) {
                    recyclerView.smoothScrollToPosition(0);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (NullUtils.isNotNull(recyclerView)) {
            outState.putInt(RECYCLER_ITEM_POSITION, ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (NullUtils.isNotNull(recyclerView)) {
            recyclerScrollToPosition = savedInstanceState.getInt(RECYCLER_ITEM_POSITION);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        contentScreenPresenter.subscribe(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        contentScreenPresenter.unSubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = getMenuItem(menu);
        item.setOnMenuItemClickListener(item1 -> {
            Intent toSearchPage = new Intent(this, SearchActivity.class);
            startActivity(toSearchPage);
            return true;
        });
        return true;
    }

    private MenuItem getMenuItem(Menu menu) {
        getMenuInflater().inflate(R.menu.content_menu, menu);
        return menu.findItem(R.id.content_screen_search);
    }
}
