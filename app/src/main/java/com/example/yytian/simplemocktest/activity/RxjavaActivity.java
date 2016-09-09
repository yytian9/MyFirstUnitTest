package com.example.yytian.simplemocktest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yytian.simplemocktest.R;
import com.example.yytian.simplemocktest.data.bean.UpdateInfo_copy;
import com.example.yytian.simplemocktest.data.bean.User;
import com.example.yytian.simplemocktest.retrofit.HBRequestApi;
import com.example.yytian.simplemocktest.retrofit.RxTransformHelper;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxjavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String RXJAVA_BASE_URL = "http://dev.api.17168.com/";
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_rxjava);

        findViewById(R.id.btn_merge_rxjava_).setOnClickListener(this);
        mTvResult = (TextView) findViewById(R.id.tv_result_show);
        mTvResult = (TextView) findViewById(R.id.btn_rxjava_packaged);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_merge_rxjava_:
                rxjava_merge_test();
                break;
            case R.id.btn_rxjava_packaged:
                rxjava_packaged();
                break;
            default:
                break;
        }
    }

    private void rxjava_merge_test(){
        User user1 = new User("yytian", "11111");
        User user2 = new User("tian", "222222");

        Observable<Integer> just1 = Observable.just(1, 2, 3);
        Observable<String> just2 = Observable.just("111", "222", "3333");
        Observable<User> just3 = Observable.just(user1, user2);

        Observable.merge(just1,just2,just3)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof User){
                            User user = (User) o;
                            Log.i("0904","这个是str类型的"+user.toString());
                        }else if(o instanceof String){
                            String str = (String) o;
                            Log.i("0904","这个是str类型的"+str);

                        }else if(o instanceof Integer){
                            Integer integer = (Integer) o;
                            Log.i("0904","这个是Integer类型的"+integer);

                        }
                    }
                });

    }

    /**
     * 实体类是jsonObject的尝试
     */
    private void rxjava_packaged() {

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
}
