package com.example.yytian.simplemocktest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yytian.simplemocktest.BaseApplication;
import com.example.yytian.simplemocktest.R;
import com.example.yytian.simplemocktest.data.bean.User;
import com.example.yytian.simplemocktest.data.ormlite.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class OrmliteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);

        findViewById(R.id.btn_ormlite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ormlite_test();
            }
        });
    }

    public void ormlite_test() {
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(BaseApplication.getApplication());
            Dao<User, Integer> userDao = helper.getUserDao();
            userDao.create(new User("yytian", "帅哥"));
            userDao.create(new User("tian", "宅男"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
