package usa.bios.animevostorg.ui.description;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.parceler.Parcels;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.databinding.ActivityDescriptionScreenBinding;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;
import usa.bios.animevostorg.ui.description.presenter.DescriptionScreenPresenter;
import usa.bios.animevostorg.ui.description.presenter.impl.DescriptionScreenPresenterImpl;
import usa.bios.animevostorg.ui.description.viewmodel.DescriptionScreenViewModel;

/**
 * Created by Bios on 10/7/2017.
 */

public class DescriptionActivity extends BaseActivity implements DescriptionScreenView {
    private static final String PAGE_ITEMS = "page_items";

    private DescriptionScreenPresenter descriptionScreenPresenter;
    private DescriptionScreenViewModel descriptionScreenViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDescriptionScreenBinding activityDescriptionScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_description_screen);
        descriptionScreenViewModel = new DescriptionScreenViewModel();
        activityDescriptionScreenBinding.setContent(descriptionScreenViewModel);

        init();
    }

    private void init() {
        descriptionScreenPresenter = new DescriptionScreenPresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        descriptionScreenPresenter.subscribe(this);
        descriptionScreenPresenter.loadPage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        descriptionScreenPresenter.unSubscribe();
    }

    @Override
    public ItemPreviewScreenViewModel getItems() {
        return Parcels.unwrap(getIntent().getParcelableExtra(PAGE_ITEMS));
    }

    @Override
    public void setImagePreview(String url) {
        descriptionScreenViewModel.descriptionImagePreview.set(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
