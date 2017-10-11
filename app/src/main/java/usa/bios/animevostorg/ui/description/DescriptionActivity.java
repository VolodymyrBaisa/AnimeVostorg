package usa.bios.animevostorg.ui.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import org.parceler.Parcels;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.description.presenter.DescriptionScreenPresenter;
import usa.bios.animevostorg.ui.description.presenter.impl.DescriptionScreenPresenterImpl;

/**
 * Created by Bios on 10/7/2017.
 */

public class DescriptionActivity extends BaseActivity implements DescriptionScreenView {
    private static final String RECYCLER_ITEM_POSITION = "content_recycler_item_position";

    private DescriptionScreenPresenter descriptionScreenPresenter;
    private ImageView preview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_screen);

        init();
    }

    private void init() {
        descriptionScreenPresenter = new DescriptionScreenPresenterImpl();

        preview = (ImageView) findViewById(R.id.descriptionImageView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        descriptionScreenPresenter.subscribe(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        descriptionScreenPresenter.unSubscribe();
    }

    @Override
    public Data getData() {
        return Parcels.unwrap(getIntent().getParcelableExtra(RECYCLER_ITEM_POSITION));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
