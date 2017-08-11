package usa.bios.animevostorg.presenters.impl;

import android.content.Context;
import android.content.Intent;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.model.pojo.SplashScreen;
import usa.bios.animevostorg.presenters.IBaseView;
import usa.bios.animevostorg.presenters.ISplashScreenPresenter;
import usa.bios.animevostorg.presenters.ISplashScreenView;
import usa.bios.animevostorg.service.APIService;
import usa.bios.animevostorg.ui.activities.ContentActivity;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashScreenPresenter implements ISplashScreenPresenter {
    private static final String ENDPOINT = "http://animevost-app.ru";
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    private WeakReference<ISplashScreenView> splashScreenViewWeakReference;
    private Observable<SplashScreen> splashScreenObservable;

    @Override
    public void loadVersion() {
        ISplashScreenView splashScreenView = splashScreenViewWeakReference.get();

        splashScreenObservable = APIService.Factory.create(splashScreenView.getCacheDir(), ENDPOINT).getVersion();
        splashScreenObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                splashScreen -> {
                    splashScreenView.loadVersion(splashScreen.getVersion());
                    splashScreenView.loadingBar(false);
                    splashScreenView.startPage();
                },
                error -> {
                    if (error instanceof HttpException) {
                        splashScreenView.showError(R.string.internet_connection_error);
                    } else {
                        splashScreenView.showError(R.string.connection_error);
                    }

                    splashScreenView.loadingBar(true);
                    splashScreenView.startPage();
                });
    }

    @Override
    public void loadPage(Context context) {
        Observable.timer(SPLASH_DISPLAY_LENGTH, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Intent toHomePage = new Intent(context, ContentActivity.class);
            context.startActivity(toHomePage);
        });
    }

    @Override
    public void subscribe(IBaseView iBaseView) {
        splashScreenViewWeakReference = new WeakReference<>((ISplashScreenView) iBaseView);
    }

    @Override
    public void unSubscribe() {
        if (splashScreenViewWeakReference != null) splashScreenViewWeakReference.clear();
        if (splashScreenObservable != null) splashScreenObservable.unsubscribeOn(Schedulers.io());
    }
}
