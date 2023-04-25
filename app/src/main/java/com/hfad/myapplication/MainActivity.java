package com.hfad.myapplication;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!= null){
          seconds = savedInstanceState.getInt("seconds");
          running = savedInstanceState.getBoolean("running");
          wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);

    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasRunning) running = true;
    }

    //sets the number of seconds in timer
    private void runTimer(){

        //get the text view
       final TextView timeView = (TextView) findViewById(R.id.time_view);

       final Handler handler = new Handler();

        //use handler to post code
       handler.post(new Runnable() {
           @Override
           public void run() {
               int hours = seconds / 3600;
               int minutes = (seconds % 3660) / 60;
               int secs = seconds % 60;

               @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d", hours, minutes, secs);
               timeView.setText(time);

               if (running) {
                   seconds++;
               }
               handler.postDelayed(this, 1000);
           }

       });

    }


    //this get start when the start button clicked
    public void onClickStart(View view){
        running = true; //start the stop watch running
    }

    //this get start when the stop button clicked
    public void onClickStop(View view){
        running= false;//stop the stop watch running
    }

    //this get start when the reset button clicked
    public void onClickReset(View view){
        //start the stop watch running and set the seconds to zero
        running = false;
        seconds = 0;
    }
}