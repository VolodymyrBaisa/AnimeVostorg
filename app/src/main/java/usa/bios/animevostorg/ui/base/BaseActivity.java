package usa.bios.animevostorg.ui.base;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.utils.NetworkUtils;

/**
 * Created by Bios on 9/16/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        TextView textView = snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        snackbar.show();
    }

    @Override
    public void onError(@StringRes int resId) {
        showSnackBar(getString(resId));
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showSnackBar(getString(resId));
    }


    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public File getCacheDir() {
        return getBaseContext().getCacheDir();
    }
}
