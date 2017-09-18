package usa.bios.animevostorg.ui.splashscreen.presenter.impl;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.BuildConfig;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.SplashScreenDao;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenView;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenInteractor;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/13/2017.
 */

public class SplashScreenInteractorImpl implements SplashScreenInteractor {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    public Disposable launchActivity(SplashScreenView splashScreenView) {
        return Observable.timer(SPLASH_DISPLAY_LENGTH, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
                    if (NullUtils.isNotNull(splashScreenView)) {
                        splashScreenView.launchActivity();
                    }
                });
    }

    @Override
    public Disposable checkVersionApp(SplashScreenView splashScreenView) {
        return APIService.Factory.create(splashScreenView.getCacheDir(), BuildConfig.SITE_URL).getVersion().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        splashScreen -> new SplashScreenDao().storeOrUpdateData(splashScreen),
                        error -> {
                            if (error instanceof HttpException) {
                                splashScreenView.onHttpError(R.string.connection_error, ((HttpException) error).code());
                            } else {
                                splashScreenView.onError(R.string.internet_connection_error);
                            }
                        });
    }


}
