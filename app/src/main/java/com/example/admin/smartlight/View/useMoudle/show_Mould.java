package com.example.admin.smartlight.View.useMoudle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.smartlight.Controller_Data.Grid.SetGrid;
import com.example.admin.smartlight.R;
import com.example.admin.smartlight.View.BluetoothConnection;

/**
 * Created by admin on 2016/11/20.
 */

public class show_Mould extends Activity {
    GridView gridView;
    Context context;
    SetGrid grid;
    TextView textView;
    ImageView imageView;

    byte[][] data = {
            {0x19, 0x24, 0x45},
            {0x19, 0x24},
            {0x35, 0x51, 0x71},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_moudle);

        textView = (TextView)findViewById(R.id.name_mould);
        imageView  = (ImageView) findViewById(R.id.image_mould);

        gridView = (GridView)findViewById(R.id.show_grid);
        context = this;

        grid = new SetGrid(context, gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();

                BluetoothConnection.blueTooth.sendMessage(BluetoothConnection.blueTooth.getSocket(),getDate(position));
            }
        });
    }

    public byte[] getDate(int position){
        return data[position];
    }
}

