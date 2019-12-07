package com.example.finalproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class clockView extends AppCompatActivity implements View.OnClickListener {

    private EditText timeInput;
    private Button get, startTime, stopTime;
    private TextView time;
    private int second = 0;
    private Timer timer = null;
    private TimerTask task = null;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        UIsetting();
        Button stop = findViewById(R.id.exit);
        stop.setOnClickListener(v -> {
            finish();
        });


        View.OnKeyListener onKey = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    onClick(get);
                    return true;
                }
                return false;
            }
        };
        timeInput.setOnKeyListener(onKey);
    }


    private void UIsetting() {
        timeInput = findViewById(R.id.input);
        get = findViewById(R.id.get);
        startTime = findViewById(R.id.starttime);
        stopTime = findViewById(R.id.stoptime);
        time = findViewById(R.id.time);
        get.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.get) {
            try {

                second = Integer.parseInt(timeInput.getText().toString());
            } catch (Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(clockView.this);
                builder.setTitle("Opppps");
                builder.setMessage("I want a correct time!!!!");
                builder.setPositiveButton("OK", null);
                builder.show();
                time.setText("");
                return;
            }
            if (second <= 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(clockView.this);
                builder.setTitle("Opppps");
                builder.setMessage("I want a positive time!!!");
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }
            //second = Integer.parseInt(timeInput.getText().toString());
            time.setText(timeInput.getText().toString());

        }
        if (v.getId() == R.id.starttime) {
            startTime();
            return;
        }
        if (v.getId() == R.id.stoptime) {
            stopTime();
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message messageTime) {
            time.setText("" + messageTime.arg1);
            System.out.println(messageTime.arg1 + " msg!!!");
            startTime();
            System.out.println("msg start time running");
            if (messageTime.arg1 == 0) {
                AlertDialog.Builder reminder = new AlertDialog.Builder(clockView.this);
                reminder.setTitle("!!!");
                reminder.setMessage("Time Up!!!");
                reminder.setPositiveButton("OK", null);
                reminder.show();
                stopTime();
            }
        }
    };


    public void stopTime() {
        timer.cancel();
    }

    public void startTime() {
//        if (timer != null) {
//            return;
//        }
        timer = new Timer();
        TimerTask countDown = new TimerTask() {
            @Override
            public void run() {
                second--;
                Message timeMessage = handler.obtainMessage();
                timeMessage.arg1 = second;
                handler.sendMessage(timeMessage);
                //开始执行handleMessage
            }
        };
        timer.schedule(countDown, 996);
    }


}
