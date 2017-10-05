package usa.bios.animevostorg.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.DataDao;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.contentscreen.adapter.ContentScreenRecyclerAdapter;
import usa.bios.animevostorg.ui.custom.OnQueryTextListener;
import usa.bios.animevostorg.ui.custom.SearchView;
import usa.bios.animevostorg.ui.search.presenter.SearchScreenPresenter;
import usa.bios.animevostorg.ui.search.presenter.impl.SearchScreenPresenterImpl;
import usa.bios.animevostorg.utils.CalcUtils;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 9/26/2017.
 */

public class SearchActivity extends BaseActivity implements SearchScreenView, OnQueryTextListener {
    private static final String RECYCLER_ITEM_POSITION = "search_recycler_item_position";

    private SearchScreenPresenter searchScreenPresenter;

    private Toolbar toolbar;
    private SearchView search;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private int recyclerScrollToPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        init();
    }

    private void init() {
        searchScreenPresenter = new SearchScreenPresenterImpl();

        toolbar = (Toolbar) findViewById(R.id.toolbarSearchLayout);
        search = (SearchView) findViewById(R.id.searchView);
        recyclerView = (RecyclerView) findViewById(R.id.contentRecyclerContainer);
        progressBar = (ProgressBar) findViewById(R.id.searchProgressBar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.contentFab);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setToolbar();
        setRecyclerView();
        setFab();
    }

    private void setToolbar() {
        if (NullUtils.isNotNull(toolbar)) {
            setSupportActionBar(toolbar);
        }
        setSearch();
        setBackButton();
    }

    private void setSearch() {
        search.setOnQueryTextListener(this);
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
        }
    }

    @Override
    public void showLoading(int visibility) {
        if (NullUtils.isNotNull(progressBar)) {
            progressBar.setVisibility(visibility);
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
        searchScreenPresenter.subscribe(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        searchScreenPresenter.unSubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        search.setMenuItem(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchScreenPresenter.fetchingSearchData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
