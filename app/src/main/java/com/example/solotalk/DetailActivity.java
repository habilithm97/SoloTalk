package com.example.solotalk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv = (TextView)findViewById(R.id.tv);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv.setText(str);
    }
}