package com.example.admin.smartlight.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.smartlight.R;
import com.example.admin.smartlight.View.createMode.createMode_create;
import com.example.admin.smartlight.View.useMoudle.show_Mould;

/**
 * Created by Administrator on 2017/4/25.
 */

public class choseMode extends Activity implements View.OnClickListener {
    Button createMode;
    Button choseMode ;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.witchmode);

        createMode = (Button)findViewById(R.id.createMode);
        choseMode = (Button)findViewById(R.id.choseMode)  ;

        createMode.setOnClickListener(this);
        choseMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createMode : jumpToNextPage(createMode_create.class);
                break;
            case R.id.choseMode  : jumpToNextPage(show_Mould.class);
                break;
        }
    }
    public void jumpToNextPage(Class className){
        Intent intent = new Intent();
        intent.setClass(this, className);
        startActivity(intent);
    }
}
