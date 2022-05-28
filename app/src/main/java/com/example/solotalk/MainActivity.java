package com.example.solotalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // 레이아웃 변화 감지
            @Override // 키보드가 올라올 때 RecyclerView의 위치를 마지막 포지션으로 이동시킴
            public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(bottom < oldBottom) { // bottom이 oldBottom에 비해서 줄어 들었다면 키보드가 올라왔다고 판단함
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(adapter.getItemCount());
                        }
                    }, 100);
                }
            }
        });

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

                    recyclerView.smoothScrollToPosition(adapter.getItemCount());

                    edt.setText("");
                }
            }
        });

        EditText searchEdt = (EditText)findViewById(R.id.searchEdt);
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력창에 변화가 생기기 전에 처리
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력창에 변화가 생길 때 처리
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력창에 변화가 생긴 후에 처리
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}