package com.example.uslessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch uselessSwitch;
    private Button selfDestruct;
    private Button lookBusy;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        progressBar.setVisibility(View.GONE);
    }

    private void setListeners() {
        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(isChecked){
//                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
//
//            }
                if(isChecked) {
                    new CountDownTimer(10000, 10) {
                        @Override
                        public void onTick(long l) {
                            if(!uselessSwitch.isChecked()){
                                cancel();
                            }

                        }

                        @Override
                        public void onFinish() {
                            uselessSwitch.setChecked(false);

                        }
                    }.start();
                }

            }
        });
        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                selfDestruct.setVisibility(View.GONE);
                uselessSwitch.setVisibility(View.GONE);
                lookBusy.setVisibility(View.GONE);
                new CountDownTimer(10000, 100){

                    int progress = 1;
                    @Override
                    public void onTick(long l) {
                        progressBar.setProgress(progress);
                        progress++;


                    }

                    @Override
                    public void onFinish() {
                        progressBar.setVisibility(View.GONE);
                        selfDestruct.setVisibility(View.VISIBLE);
                        uselessSwitch.setVisibility(View.VISIBLE);
                        lookBusy.setVisibility(View.VISIBLE);

                    }
                }.start();

            }
        });
        selfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 1000) {
                    int x = 10;
                    @Override

                    public void onTick(long l) {

                        selfDestruct.setText(String.valueOf(x));
                        x--;


                    }

                    @Override
                    public void onFinish() {
                       int x = 1/0;


                    }
                }.start();
            }
        });
    }

    private void wireWidgets() {
        uselessSwitch = findViewById(R.id.switch_main_useless);
        selfDestruct = findViewById(R.id.button_main_selfDestruct);
        lookBusy = findViewById(R.id.button_main_lookbusy);
        progressBar = findViewById(R.id.progressBar_main_busyBar);
    }
}
