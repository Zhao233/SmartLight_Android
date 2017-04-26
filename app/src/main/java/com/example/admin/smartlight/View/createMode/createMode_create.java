package com.example.admin.smartlight.View.createMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.admin.smartlight.R;
import com.example.admin.smartlight.View.LightButton.LightButton;

/**
 * Created by Administrator on 2017/4/23.
 */

public class createMode_create extends Activity implements View.OnClickListener{
    int[] bars_id = { R.id.light_change_1,
                      R.id.light_change_2,
                      R.id.light_change_3};

    int[] values_id = { R.id.light_change_1_value,
                        R.id.light_change_2_value,
                        R.id.light_change_3_value};

    int[] button_id = { R.id.light_1,
                        R.id.light_2,
                        R.id.light_3,
                        R.id.light_4,
                        R.id.light_5,
                        R.id.light_6,
                        R.id.light_7,
                        R.id.light_8,
                        R.id.light_9,
                        R.id.light_10,
                        R.id.light_11,
                        R.id.light_12,
                        R.id.light_13,
                        R.id.light_14,
                        R.id.light_15 };

    SeekBar[] bars = new SeekBar[3];
    TextView[] values = new TextView[3];
    LightButton[] buttons = new LightButton[15];

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_moudle);

        for(int i = 0; i < 3; i++){
            bars[i] = (SeekBar) findViewById(bars_id[i]);
            values[i] = (TextView) findViewById(values_id[i]);

            bars[i].setOnSeekBarChangeListener(new seekBarChangeLisener(values[i]));
        }

        for(int i = 0; i < button_id.length; i++){
            buttons[i] = (LightButton) findViewById(button_id[i]);
            buttons[i].setOnClickListener(this);
        }




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.create_moudle, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.saveMode){
            Intent toNextActivity = new Intent();
            toNextActivity.setClass(this, createMode_save.class);
            startActivity(toNextActivity);
        }
        return true;
    }

    public void sendMessage(String mes){}

    @Override
    public void onClick(View v) {
        LightButton button = (LightButton) v;
        button.changeLightState();
        sendMessage(button.MESSAGE);
    }

    class seekBarChangeLisener implements SeekBar.OnSeekBarChangeListener{
        TextView value;

        public seekBarChangeLisener(TextView newValue){
            value = newValue;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            value.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sendMessage(String.valueOf(value.getText()));
        }
    }
}

