package usa.bios.animevostorg.ui.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.base.BaseActivity;

/**
 * Created by Bios on 10/7/2017.
 */

public class DescriptionActivity extends BaseActivity {

    private ImageView preview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_screen);

        init();
    }

    private void init() {

        preview = (ImageView) findViewById(R.id.descriptionImageView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
