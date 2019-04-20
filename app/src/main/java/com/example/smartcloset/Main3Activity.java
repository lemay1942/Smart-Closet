package com.example.smartcloset;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private RecyclerAdapter adapter;
    private Stop_DBHelper helperlogin;
    private Stop_DBBasic dbbasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button addButton = (Button)findViewById(R.id.addButton);
        dbbasic = new Stop_DBBasic();
        helperlogin = helperlogin.getinstence(getApplicationContext());

        final Intent addIntent = new Intent(this, AddActivity.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addIntent);
            }
        });

        init();

        getData();

    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        List<Integer> listResId = Arrays.asList(
                R.drawable.sad,
                R.drawable.surprised,
                R.drawable.happy
        );
        Cursor cur = dbbasic.select(new Stop_DBLogin(helperlogin.getReadableDatabase()), "select * from Clothe"); //주석
        for(int i = 0; i < cur.getCount(); i++){
            Data data = new Data();
            cur.moveToNext();
            data.setTitle(cur.getString(1));
            data.setContent(cur.getString(2));
            data.setResId(listResId.get(2));

            adapter.additem(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //빽(취소)키가 눌렸을때 종료여부를 묻는 다이얼로그 띄움
        if((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder d = new AlertDialog.Builder(Main3Activity.this);
            d.setTitle("종료여부");
            d.setMessage("정말 종료 하시겠습니꺄?");
            d.setIcon(R.drawable.teamicon);

            d.setPositiveButton("예",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    Main3Activity.this.finish();
                }
            });

            d.setNegativeButton("아니요",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.cancel();
                }
            });
            d.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
