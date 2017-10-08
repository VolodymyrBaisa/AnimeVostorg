package usa.bios.animevostorg.ui.custom.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 10/1/2017.
 */

public class SearchView extends FrameLayout {

    private Context context;

    private OnQueryTextListener onQueryChangeListener;

    private EditText searchTextView;
    private ImageView actionEmptyBtn;

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
        if (a != null) {
            if (a.hasValue(R.styleable.SearchView_android_textColor)) {
                setTextColor(a.getColor(R.styleable.SearchView_android_textColor, 0));
            }

            if (a.hasValue(R.styleable.SearchView_android_textColorHint)) {
                setHintTextColor(a.getColor(R.styleable.SearchView_android_textColorHint, 0));
            }

            if (a.hasValue(R.styleable.SearchView_android_hint)) {
                setHint(a.getString(R.styleable.SearchView_android_hint));
            }

            if (a.hasValue(R.styleable.SearchView_searchCloseIcon)) {
                setCloseIcon(a.getDrawable(R.styleable.SearchView_searchCloseIcon));
            }
            a.recycle();
        }
    }

    private void initiateView() {
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);

        LinearLayout searchLayout = findViewById(R.id.searchLayout);

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

        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SearchView.this.onTextChanged(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showSearch();
        showKeyboard(searchTextView);
    }

    private void onSubmitQuery() {
        CharSequence query = searchTextView.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (onQueryChangeListener == null || !onQueryChangeListener.onQueryTextSubmit(query.toString())) {
                closeSearch();
            }
        }
    }

    private void showSearch() {
        searchTextView.setText(null);
        searchTextView.requestFocus();
    }

    private void closeSearch() {
        searchTextView.setText(null);
        super.clearFocus();
        clearFocus();
    }

    private void showKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (NullUtils.isNotNull(imm)) {
            imm.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (NullUtils.isNotNull(imm)) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void onTextChanged(CharSequence newText) {
        CharSequence text = searchTextView.getText();
        boolean hasText = !TextUtils.isEmpty(text);
        if (hasText) {
            actionEmptyBtn.setVisibility(VISIBLE);
        } else {
            actionEmptyBtn.setVisibility(GONE);
        }
    }

    @Override
    public void clearFocus() {
        hideKeyboard(searchTextView);
        super.clearFocus();
        searchTextView.clearFocus();
    }

    public void setMenuItem(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            closeSearch();
        }
    }

    public void setOnQueryTextListener(OnQueryTextListener listener) {
        onQueryChangeListener = listener;
    }

    public void setTextColor(int color) {
        searchTextView.setTextColor(color);
    }

    public void setHintTextColor(int color) {
        searchTextView.setHintTextColor(color);
    }

    public void setHint(CharSequence hint) {
        searchTextView.setHint(hint);
    }

    public void setCloseIcon(Drawable drawable) {
        actionEmptyBtn.setImageDrawable(drawable);
    }

    private final OnClickListener onClickListener = new OnClickListener() {

        public void onClick(View v) {
            if (v == actionEmptyBtn) {
                searchTextView.setText(null);
            }
        }

    };
}
