package usa.bios.animevostorg.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import usa.bios.animevostorg.R;

/**
 * Created by Bios on 10/1/2017.
 */

public class SearchView extends FrameLayout implements Filter.FilterListener, OnQueryTextListener {

    private Context context;

    private OnQueryTextListener onQueryChangeListener;
    private SearchViewListener searchViewListener;

    private LinearLayout searchLayout;
    private EditText searchTextView;
    private ImageView actionEmptyBtn;
    private boolean isSearchOpen = false;

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        initiateView();
        initStyle(attrs, defStyleAttr);
    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView, defStyleAttr, 0);
                    a.recycle();
    }

    private void initiateView() {
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);

        searchLayout = findViewById(R.id.search_layout);

        searchTextView = searchLayout.findViewById(R.id.searchTextView);
        actionEmptyBtn = searchLayout.findViewById(R.id.actionEmptyBtn);

        actionEmptyBtn.setOnClickListener(onClickListener);
        initSearchView();
    }

    private void initSearchView() {
        searchTextView.setOnEditorActionListener((v, actionId, event) -> {
            onSubmitQuery();
            return true;
        });
    }

    private void onSubmitQuery() {
        CharSequence query = searchTextView.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (onQueryChangeListener == null || !onQueryChangeListener.onQueryTextSubmit(query.toString())) {
                closeSearch();
            }
        }
    }

    public void setMenuItem(MenuItem menuItem) {
        menuItem.setOnMenuItemClickListener(item -> {
            showSearch();
            return true;
        });
    }

    public void showSearch() {
        if (isSearchOpen()) {
            return;
        }
        searchTextView.setText(null);
        searchTextView.requestFocus();

        searchTextView.setVisibility(VISIBLE);
        if (searchViewListener != null) {
            searchViewListener.onSearchViewShown();
        }
        isSearchOpen = true;
    }

    public void closeSearch() {
        if (!isSearchOpen()) {
            return;
        }

        searchTextView.setText(null);
        clearFocus();

        searchLayout.setVisibility(GONE);
        if (searchViewListener != null) {
            searchViewListener.onSearchViewClosed();
        }
        isSearchOpen = false;
    }

    public boolean isSearchOpen() {
        return isSearchOpen;
    }

    @Override
    public void onFilterComplete(int i) {

    }

    public void setOnQueryTextListener(OnQueryTextListener listener) {
        onQueryChangeListener = listener;
    }

    public void setOnSearchViewListener(SearchViewListener listener) {
        searchViewListener = listener;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private final OnClickListener onClickListener = new OnClickListener() {

        public void onClick(View v) {
            if (v == actionEmptyBtn) {
                searchTextView.setText(null);
            }
        }

    };
}
