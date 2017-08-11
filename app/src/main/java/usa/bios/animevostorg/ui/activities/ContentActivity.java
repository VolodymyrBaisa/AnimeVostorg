package usa.bios.animevostorg.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.helpers.NullHelper;
import usa.bios.animevostorg.helpers.TypefaceHelper;
import usa.bios.animevostorg.presenters.IContentPresenter;
import usa.bios.animevostorg.presenters.IContentView;
import usa.bios.animevostorg.presenters.impl.ContentPresenter;

/**
 * Created by Bios on 8/9/2017.
 */

public class ContentActivity extends AppCompatActivity implements IContentView {
    IContentPresenter contentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);
        init();
    }

    private void init() {
        contentPresenter = new ContentPresenter();

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLayout);
        if(!NullHelper.isNull(toolbar)) {
            setSupportActionBar(toolbar);
        }

        TextView toolbarLabel = (TextView) findViewById(R.id.toolbarLabel);

        if(!NullHelper.isNull(toolbarLabel)) {
            toolbarLabel.setText(R.string.app_name);
            String font = "fonts/roomfer.ttf";
            toolbarLabel.setTypeface(TypefaceHelper.font(this, font));
        }

        ActionBar actionBar = getSupportActionBar();
        if (!NullHelper.isNull(actionBar)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
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
