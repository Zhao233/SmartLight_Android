package com.example.admin.smartlight.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.smartlight.Controller_Bluetooth.BlueTooth;
import com.example.admin.smartlight.Controller_Data.List.SetList;
import com.example.admin.smartlight.R;

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

        blueTooth = new BlueTooth(this);
        context = this;
        handle = new Handler();

        listView = (ListView)findViewById(R.id.show_buletoothDevice);
        button_Scan = (Button)findViewById(R.id.scanButton);

        button_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !blueTooth.isOpen() ){
                    Toast.makeText(context, "蓝牙未打开，正在打开蓝牙", Toast.LENGTH_LONG);

                    startActivity(blueTooth.On());//打开蓝牙
                }
                blueTooth.updata();

                names = blueTooth.getNamesOfBluetoothDevice();
                address = blueTooth.getAddressOfBluetoothDevice();
                list_show = new SetList(listView, context, names, address); //show the List
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
                try {

                    if( blueTooth.connect(blueTooth.getBluetoothDevice(position)) ){
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
                Thread.sleep(1000);

                Intent intent =new Intent();
                intent.setClass(BluetoothConnection.this, show_Mould.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


