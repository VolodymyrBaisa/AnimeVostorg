package usa.bios.animevostorg.service;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.realm.RealmList;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import usa.bios.animevostorg.BuildConfig;
import usa.bios.animevostorg.model.DataList;
import usa.bios.animevostorg.model.RealmString;
import usa.bios.animevostorg.model.SplashScreen;
import usa.bios.animevostorg.utils.RealmStringListTypeAdapter;

/**
 * Created by Bios on 8/6/2017.
 */

public interface APIService {
    @GET("/version.json")
    Observable<SplashScreen> getVersion();

    @GET("/v1/last?")
    Observable<DataList> getData(
            @Query("page") Integer page,
            @Query("quantity") Integer quantity
    );

    @FormUrlEncoded
    @POST("/v1/search")
    Observable<DataList> getFilteredData(
            @Field("gen") String gen,
            @Field("name") String name,
            @Field("year") String year,
            @Field("cat") String cat
    );

    class Factory {
        public static APIService create(File directory, String endpoint) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);


            builder.addNetworkInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader("User-Agent", BuildConfig.USER_AGENT).build();
                return chain.proceed(request);
            });

            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(directory, cacheSize);
            builder.cache(cache);

            GsonConverterFactory gsonConFactory = GsonConverterFactory
                    .create(new GsonBuilder()
                            .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
                                    }.getType(),
                                    RealmStringListTypeAdapter.INSTANCE).create());

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .client(client)
                    .addConverterFactory(gsonConFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}
