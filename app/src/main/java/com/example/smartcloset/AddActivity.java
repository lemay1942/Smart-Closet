package com.example.smartcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    String substitute = "";
    String clothName = "";
    String moreInf = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final EditText clothText = (EditText)findViewById(R.id.clothText);
        EditText infText = (EditText)findViewById(R.id.infText);
        Button cancleButton = (Button)findViewById(R.id.cancleButton);
        Button inputButton = (Button)findViewById(R.id.inputButton);

        final Intent inputIntent = new Intent(this, Main4Activity.class);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                substitute = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        clothName = clothText.getText().toString();
        moreInf = infText.getText().toString();

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(substitute.equals("") || clothText.equals("") || moreInf.equals("")){
                    Toast.makeText(AddActivity.this, "정보가 제대로 입력되지 않았습니다.", Toast.LENGTH_LONG).show(); //주석
                }else {
                    startActivity(inputIntent);
                    finish();
                }
            }
        });
    }
}
