package com.example.yytian.simplemocktest.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yytian.simplemocktest.BaseApplication;
import com.example.yytian.simplemocktest.R;

public class MainActivity extends AppCompatActivity implements LoginContact.View {

    EditText mEtUserName;
    EditText mEtPassword;
    TextView mTvLoadingState;
    TextView mTvLoginResult;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginPresenter = new LoginPresenter(this,new LoginManager());

        mEtUserName= (EditText) findViewById(R.id.et_user_name);
        mEtPassword= (EditText) findViewById(R.id.et_password);
        mTvLoadingState= (TextView) findViewById(R.id.tv_loading_state);
        mTvLoginResult= (TextView) findViewById(R.id.tv_login_result);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(mEtUserName.getText().toString().trim(),mEtPassword.getText().toString().trim());
            }
        });

        findViewById(R.id.btn_retrofit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }


    @Override
    public void setPresenter(LoginContact.Presenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            mTvLoadingState.setText("Loading…………");
        }else {
            mTvLoadingState.setText("");
        }
    }

    @Override
    public void showLoginResult(String meg) {
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
