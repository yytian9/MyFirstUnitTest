package com.example.yytian.simplemocktest.retrofit;

import com.example.yytian.simplemocktest.BaseApplication;
import com.example.yytian.simplemocktest.utils.NetworkUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author： yytian
 * time：   2016/8/31 19:35
 * des：
 */
public class HBRxjavaService {

    private static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB
    //缓存路径

    private static Cache mCache = new Cache(new File(BaseApplication.getApplication().getCacheDir(), "network_cache")
            , CACHE_SIZE);

    private static RewriteCacheControlInterceptor mCacheInterceptor = new RewriteCacheControlInterceptor();

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            return response;
        }
    }

    private static class RewriteCacheControlInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetworkUtils.isNetworkConnected(BaseApplication.getApplication())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);

            if (NetworkUtils.isNetworkConnected(BaseApplication.getApplication())) {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
            return originalResponse;
        }
    }

    private static OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .cache(mCache)
            .addInterceptor(mCacheInterceptor)
            .addNetworkInterceptor(mCacheInterceptor)
            .retryOnConnectionFailure(true)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build();

    private static Gson mGson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private static Retrofit mRetrofit = new Retrofit.Builder()
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl("http://dev.api.17168.com/")
            .build();

    private HBRxjavaService() {
    }

    public static <T> T createService(Class<T> mClass) {
        return mRetrofit.create(mClass);
    }
}
