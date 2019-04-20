package com.example.smartcloset;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Main4Activity extends AppCompatActivity {
    private Stop_DBHelper helperlogin;
    private Stop_DBBasic dbbasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        dbbasic = new Stop_DBBasic();
        helperlogin = helperlogin.getinstence(getApplicationContext());

        Button mainButton = (Button) findViewById(R.id.mainButton);
        TextView TV = (TextView) findViewById(R.id.textView);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Cursor cur = dbbasic.select(new Stop_DBLogin(helperlogin.getReadableDatabase()), "select * from Clothe");
        if (cur.getCount() != 0) {
            while (cur.moveToNext()) {
                if(cur.getCount() == cur.getPosition())
                    break;
                else
                    TV.setText(cur.getString(2));

            }
        }else{
            Toast.makeText(Main4Activity.this, "옷 정보를 먼저 등록해주세요", Toast.LENGTH_LONG).show();
        }
    }
}
