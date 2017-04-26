package com.example.admin.smartlight.View.LightButton;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.admin.smartlight.R;

/**
 * Created by Administrator on 2017/4/26.
 */

public class LightButton extends Button{
    public int light = 0;
    public final int ON = 1;
    public final int OFF = 0;

    public final String MESSAGE = "";

    public LightButton(Context context) {
        super(context);
    }

    public LightButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LightButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void changeLightState() {
        switch (light){
            case OFF :
                light = ON;
                super.setTextColor(this.getResources().getColor(R.color.black));

                break;
            case ON :
                light = OFF;
                super.setTextColor(this.getResources().getColor(R.color.write));

                break;
        }
    }
}
