package com.example.yytian.simplemocktest.login;

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
