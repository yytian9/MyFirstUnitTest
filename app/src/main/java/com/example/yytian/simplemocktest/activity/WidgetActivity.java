package com.example.yytian.simplemocktest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yytian.simplemocktest.R;
import com.example.yytian.simplemocktest.widget.ProgressView;

public class WidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedgit);

        ProgressView pv = (ProgressView) findViewById(R.id.pv);
        pv.setNote("天空");
        pv.setMax(100);
        pv.setProgress(50);
        //为了背景能绘制
        pv.setBackgroundColor(Color.TRANSPARENT);

    }
}
