package com.example.solotalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TalkAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Talk> items;

    EditText edt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); // 아이템을 추가해도 RecyclerView의 크기가 변하지 않음

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TalkAdapter(items);
        recyclerView.setAdapter(adapter);

        edt = (EditText)findViewById(R.id.edt);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 처리
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력창에 변화가 있을 때 처리
                // 입력창에 변화가 있을 때는 보내기 버튼의 TextColor를 파란색으로하고,
                // 변화가 없을 때는 그대로 회색으로 함
                btn.setTextColor(Color.BLUE);
                if(edt.getText().toString().equals("")) {
                    btn.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력이 끝났을 때 처리
            }
        });

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt.getText().toString().equals("")) {
                    String str = edt.getText().toString();

                    adapter.addItem(new Talk(str));
                    adapter.notifyDataSetChanged();

                    edt.setText("");
                }
            }
        });
    }
}