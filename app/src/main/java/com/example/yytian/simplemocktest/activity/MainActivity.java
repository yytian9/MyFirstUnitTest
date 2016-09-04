package com.example.yytian.simplemocktest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yytian.simplemocktest.BaseApplication;
import com.example.yytian.simplemocktest.R;
import com.example.yytian.simplemocktest.data.api.ApiService;
import com.example.yytian.simplemocktest.data.api.UpdateApi;
import com.example.yytian.simplemocktest.data.bean.UpdateInfo;
import com.example.yytian.simplemocktest.data.bean.UpdateInfo_copy;
import com.example.yytian.simplemocktest.data.bean.User;
import com.example.yytian.simplemocktest.data.ormlite.DatabaseHelper;
import com.example.yytian.simplemocktest.login.LoginContact;
import com.example.yytian.simplemocktest.login.LoginManager;
import com.example.yytian.simplemocktest.login.LoginPresenter;
import com.example.yytian.simplemocktest.retrofit.BaseEntity;
import com.example.yytian.simplemocktest.retrofit.BaseEntity_copy;
import com.example.yytian.simplemocktest.retrofit.HBRequestApi;
import com.example.yytian.simplemocktest.retrofit.HBRequestApi_copy;
import com.example.yytian.simplemocktest.retrofit.HBRxjavaService;
import com.example.yytian.simplemocktest.retrofit.RxTransformHelper;
import com.example.yytian.simplemocktest.retrofit.UpdateInfoData;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LoginContact.View {

    EditText mEtUserName;
    EditText mEtPassword;
    TextView mTvLoadingState;
    TextView mTvLoginResult;
    private LoginPresenter mLoginPresenter;

    private static final String ENDPOINT = "http://ip.taobao.com/";
    private static final String RXJAVA_BASE_URL = "http://dev.api.17168.com/";
    private TextView mTvHttpResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginPresenter = new LoginPresenter(this, new LoginManager());

        mEtUserName = (EditText) findViewById(R.id.et_user_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvLoadingState = (TextView) findViewById(R.id.tv_loading_state);
        mTvLoginResult = (TextView) findViewById(R.id.tv_login_result);
        mTvHttpResult = (TextView) findViewById(R.id.tv_http_result_show);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(mEtUserName.getText().toString().trim(), mEtPassword.getText().toString().trim());
            }
        });

        findViewById(R.id.btn_retrofit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit_test();
            }
        });

        findViewById(R.id.btn_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxjava_test();
            }
        });

        findViewById(R.id.btn_ormlite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ormlite_test();
            }
        });
        findViewById(R.id.btn_rxjava_packaged).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rxjava_packaged_test();
                rxjava_packaged_test_copy2();
            }
        });
        findViewById(R.id.btn_to_widget_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WidgetActivity.class));
            }
        });

    }

    /**
     * 对rxjava进行封装的测试
     */
    private void rxjava_packaged_test() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RXJAVA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HBRequestApi requestApi = retrofit.create(HBRequestApi.class);

        requestApi.httpGetRequest("82101","","11111111111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<BaseEntity, UpdateInfoData>() {
                    @Override
                    public UpdateInfoData call(BaseEntity baseEntity) {

                        //TODO  下面可能会抛出解析异常，想下怎么处理
                        return new Gson().fromJson(baseEntity.data.toString(), UpdateInfoData.class);
                    }
                })
                .subscribe(new Observer<UpdateInfoData>() {
                    @Override
                    public void onCompleted() {
                        Log.i("hhhhhh","2222");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        Log.i("hhhhhh",throwable.toString());
                    }

                    @Override
                    public void onNext(UpdateInfoData data) {
                        Log.i("hhhhhh",data.toString());
                    }
                });
    }
    /**
     * 实体类是jsonObject的尝试
     */
    private void rxjava_packaged_test_copy2() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RXJAVA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HBRequestApi requestApi = retrofit.create(HBRequestApi.class);

        requestApi.httpGetRequest("82101","","11111111111")
                .compose(RxTransformHelper.handleResult(UpdateInfo_copy.class))
                .subscribe(new Observer<UpdateInfo_copy>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateInfo_copy updateInfo_copy) {
                        Log.i("hhhhhh",updateInfo_copy.toString());
                    }
                });
    }
    /**
     * this scheme can't be effect,since the request's method of retrofit can't be generics
     */
    private void rxjava_packaged_test_copy() {

        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RXJAVA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        HBRequestApi_copy requestApiCopy = retrofit.create(HBRequestApi_copy.class);

        requestApiCopy.httpGetRequest("82101","","11111111111",UpdateInfo_copy.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity_copy<UpdateInfo_copy>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseEntity_copy<UpdateInfo_copy> updateInfo) {
                        Log.i("yytian",updateInfo.toString());
                    }
                });
    }


    /**
     * save for the method,this request will course a 504 http error,the logcat show:
     *  retrofit2.adapter.rxjava.HttpException: HTTP 504 Unsatisfiable Request (only-if-cached)
     *  fix it when time is free
     */
    private void _rxjava_packaged_test() {
        HBRequestApi requestApi = HBRxjavaService.createService(HBRequestApi.class);
        requestApi.httpGetRequest("82101","","11111111111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<BaseEntity, UpdateInfoData>() {
                    @Override
                    public UpdateInfoData call(BaseEntity baseEntity) {
                        return new Gson().fromJson(baseEntity.data.toString(),UpdateInfoData.class);
                    }
                })
                .subscribe(new Observer<UpdateInfoData>() {
                    @Override
                    public void onCompleted() {
                        Log.i("hhhhhh","2222");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        Log.i("hhhhhh",throwable.toString());
                    }

                    @Override
                    public void onNext(UpdateInfoData data) {
                        Log.i("hhhhhh",data.toString());
                    }
                });
    }

    /**
     * a simple test for ormlite
     */
    private void ormlite_test() {
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(BaseApplication.getApplication());
            Dao<User, Integer> userDao = helper.getUserDao();
            userDao.create(new User("yytian", "帅哥"));
            userDao.create(new User("tian", "宅男"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * a simple test for rxjava
     */
    private void rxjava_test() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RXJAVA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UpdateApi updateApi = retrofit.create(UpdateApi.class);

        updateApi.getUpdateInfo("82101","1111111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        mTvHttpResult.setText(updateInfo.data.download_url);
                    }
                });

    }

    /**
     * a simple test for retrofit
     */
    private void retrofit_test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);


        Call<String> call = apiService.getIpInfo("21.22.11.33");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                String body = response.body();
                String result = body.toString();
                Log.i("yytian",result);
                Toast.makeText(MainActivity.this, result,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void setPresenter(LoginContact.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mTvLoadingState.setText("Loading…………");
        } else {
            mTvLoadingState.setText("");
        }
    }

    @Override
    public void showLoginResult(java.lang.String meg) {
        mTvLoginResult.setText(meg);
    }

    @Override
    public void showErrorView() {
        Toast.makeText(BaseApplication.getApplication(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessView() {
        Toast.makeText(BaseApplication.getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
    }
}
