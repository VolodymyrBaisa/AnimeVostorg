package usa.bios.animevostorg.ui.splashscreen.presenter.impl;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.dao.SplashScreenDao;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenView;
import usa.bios.animevostorg.ui.splashscreen.listener.LauncherActivityListener;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenInteractor;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/13/2017.
 */

public class SplashScreenInteractorImpl implements SplashScreenInteractor {
    private static final String ENDPOINT = "http://animevost-app.ru";
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    private LauncherActivityListener launcherActivityListener;

    public SplashScreenInteractorImpl(LauncherActivityListener launcherActivityListener) {
        this.launcherActivityListener = launcherActivityListener;
    }

    @Override
    public Disposable loadVersion(SplashScreenView splashScreenView) {

        return APIService.Factory.create(splashScreenView.getCacheDir(), ENDPOINT).getVersion().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        splashScreen -> {
                            new SplashScreenDao().storeOrUpdateData(splashScreen);
                            launcherActivityListener.launchActivity();
                        },
                        error -> {
                            if (error instanceof HttpException) {
                                splashScreenView.showError(R.string.internet_connection_error);
                            } else {
                                splashScreenView.showError(R.string.connection_error);
                            }
                            launcherActivityListener.launchActivity();
                        });
    }

    @Override
    public Disposable loadPage(SplashScreenView splashScreenView) {
        return Observable.timer(SPLASH_DISPLAY_LENGTH, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
                    if (NullUtils.isNotNull(splashScreenView)) {
                        splashScreenView.loadContentPage();
                    }
                });
    }


}
