package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.Begin);
        TextView textView = findViewById(R.id.textView);
        //ListView setting = findViewById(R.id.clockList);
        //textView.setText("Record your moments!!!");
        //textView.setVisibility(View.GONE);
        Intent intent = new Intent(this, clockView.class);


        //View messageChunk = getLayoutInflater().inflate(R.layout.timer, null, false);
        //Button startbut = messageChunk.findViewById(R.id.starttime);
        start.setOnClickListener(v -> {
//            textView.setVisibility(View.GONE);
            startActivity(intent);


        });
        Button stop = findViewById(R.id.button);
        stop.setOnClickListener(v -> {
            finish();
        });
    }

}
