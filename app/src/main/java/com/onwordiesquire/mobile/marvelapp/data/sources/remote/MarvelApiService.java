package com.onwordiesquire.mobile.marvelapp.data.sources.remote;

import android.os.Build;
import android.os.Debug;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataWrapper;
import com.onwordiesquire.mobile.marvelapp.util.MyGsonTypeAdapterFactory;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by michelonwordi on 10/23/16.
 */

public interface MarvelApiService {

    public String ENDPOINT = "http://gateway.marvel.com:80/v1/public/";

    @GET("characters")
    public Observable<CharacterDataWrapper> getMarvelCharacter(@Query("name") String name, @Query("apikey") String apikey,
                                                               @Query("ts") String timestamp, @Query("hash") String md5Hash);


    class HELPER {

        public static MarvelApiService newMarvelApiService() {
            OkHttpClient client = null;
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            } else {
                client = new OkHttpClient.Builder().build();

            }


            Gson gson = new GsonBuilder().registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

            return retrofit.create(MarvelApiService.class);
        }

        public static String createHash(String timestamp, String apikey, String privateKey) {
            return new String(Hex.encodeHex(DigestUtils.md5(timestamp + privateKey + apikey)));
        }
    }
}
