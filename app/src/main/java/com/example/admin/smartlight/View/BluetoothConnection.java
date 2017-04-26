package com.example.admin.smartlight.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.smartlight.Controller_Bluetooth.BlueTooth;
import com.example.admin.smartlight.Controller_Data.List.SetList;
import com.example.admin.smartlight.R;
import com.example.admin.smartlight.View.useMoudle.show_Mould;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 2016/11/20.
 */

public class BluetoothConnection extends Activity{
    public static BlueTooth blueTooth;

    ListView listView;
    Button button_Scan;
    SetList list_show;

    ArrayList<String> names = null;
    ArrayList<String> address = null;

    Context context;
    Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_connection);
        context = this;
        handle = new Handler();

        listView = (ListView)findViewById(R.id.show_buletoothDevice);
        button_Scan = (Button)findViewById(R.id.scanButton);
        list_show = new SetList(listView, context);//show a empty view before scan

        blueTooth = new BlueTooth(this, new Handler(), list_show);
        button_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !blueTooth.isOpen() ){
                    Toast.makeText(context, "蓝牙未打开，正在打开蓝牙", Toast.LENGTH_LONG);

                    startActivity(blueTooth.On());//打开蓝牙
                }

                Toast.makeText(context,"扫描中....",Toast.LENGTH_LONG).show();
                blueTooth.ScanDevice();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"connecting",Toast.LENGTH_LONG).show();
                Log.i("connecting",String.valueOf(position));
                try {
                    if( blueTooth.connect(blueTooth.getBluetoothDevice(position)) ){
                        Toast.makeText(context, "连接成功", Toast.LENGTH_SHORT).show();
                        new timeCounter(handle).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class timeCounter extends Thread{
        Handler handle;

        timeCounter(Handler handle){
            this.handle = handle;
        }

        @Override
        public void run(){
            try {
                Thread.sleep(500);

                Intent intent =new Intent();
                intent.setClass(BluetoothConnection.this, choseMode.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


