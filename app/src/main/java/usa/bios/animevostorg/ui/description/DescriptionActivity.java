package usa.bios.animevostorg.ui.description;

import android.os.Bundle;
import android.support.annotation.Nullable;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.base.BaseActivity;

/**
 * Created by Bios on 10/7/2017.
 */

public class DescriptionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_screen);

        init();
    }

    private void init() {
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
