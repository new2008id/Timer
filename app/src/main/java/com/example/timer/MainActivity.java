package com.example.timer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTimer;
    private boolean isTimerRunning = false;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewTimer = findViewById(R.id.textViewTimer);
        runTimer();

    }

    public void buttonStartClick(View view) {
        isTimerRunning = true;
    }

    public void buttonPauseClick(View view) {
        isTimerRunning = false;
    }

    public void buttonResetClick(View view) {
        isTimerRunning = false;
        seconds = 0;
    }

    private void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hourses = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hourses, minutes, sec);
                textViewTimer.setText(time);

                if (isTimerRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    //    android:configChanges="orientation|screenSize" // NOT bad practise площая практика решения поворота экрана

    // good practise!

}
