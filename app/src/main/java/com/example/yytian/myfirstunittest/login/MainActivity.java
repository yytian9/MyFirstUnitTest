package com.example.yytian.myfirstunittest.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yytian.myfirstunittest.R;

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

        mLoginPresenter = new LoginPresenter(this);

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
}
