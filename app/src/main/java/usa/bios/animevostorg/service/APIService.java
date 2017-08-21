package usa.bios.animevostorg.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import usa.bios.animevostorg.model.Data;
import usa.bios.animevostorg.model.RealmDataList;
import usa.bios.animevostorg.model.SplashScreen;

/**
 * Created by Bios on 8/6/2017.
 */

public interface APIService {
    String USER_AGENT = "Anime Vostorg";

    @GET("/version.json")
    Observable<SplashScreen> getVersion();

    @GET("/v1/last?")
    Observable<List<Data>> getData(
            @Query("page") Integer page,
            @Query("quantity") Integer quantity
    );

    class Factory {
        public static APIService create(File directory, String endpoint) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);


            builder.addNetworkInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader("User-Agent", USER_AGENT).build();
                return chain.proceed(request);
            });

            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(directory, cacheSize);
            builder.cache(cache);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}
