package com.example.admin.smartlight.Controller_Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.widget.Toast;

import com.example.admin.smartlight.Controller_Data.List.SetList;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2016/9/10.
 */
public class BlueTooth extends Observable {
    public SetList listAdapter;

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private ArrayList<BluetoothDevice> remoteDevice;
    private BluetoothSocket bluetoothSocket;
    private Context context;
    private Handler handler;

    private static final int SCAN_DELAY = 3000;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if( !remoteDevice.contains(device) ){
                    remoteDevice.add(device);
                    listAdapter.addData(device.getName(), device.getAddress());
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                BA.cancelDiscovery();
            }
        }
    };


    public BlueTooth(Context newContext, Handler newHandle, SetList list) {
        this.handler = newHandle;
        BA = BluetoothAdapter.getDefaultAdapter();

        pairedDevices = BA.getBondedDevices();

        remoteDevice = new ArrayList<>();

        this.context = newContext;

        listAdapter = list;

        /*AcceptThread a = new AcceptThread();
        a.start();*/

    }


    public BluetoothSocket getSocket() {
        return bluetoothSocket;
    }



    public BluetoothAdapter getBluetoothAdapter() {
        return BA;
    }

    public Set<BluetoothDevice> getPairedDevices() {
        return pairedDevices;
    }

    /*Turn on the bluetooth device*/
    public Intent On() {
        BluetoothAdapter tempBA = this.BA;

        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        Toast.makeText(context, "BlueTooth TurnOn", Toast.LENGTH_LONG).show();

        return turnOn;
    }

    public void updata(){
        while(BA.enable()){
            BA = getBluetoothAdapter();
            pairedDevices = BA.getBondedDevices();
            break;
        }
    }

    /*Get the address of these device, and put them into a list*/
    public ArrayList<String> getAddressOfBluetoothDevice() {
        ArrayList<String> list1 = new ArrayList<String>();

        for (BluetoothDevice btd : pairedDevices) {
            list1.add(btd.getAddress());
        }

        return list1;
    }

    /*Get the names of BlueTooth Device, and put them into a List*/
    public ArrayList<String> getNamesOfBluetoothDevice() {
        ArrayList<String> list = new ArrayList<String>();

        for (BluetoothDevice btd : pairedDevices) {
            list.add(btd.getName());
        }

        return list;
    }

    public void ScanDevice(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "停止扫描", Toast.LENGTH_SHORT).show();
                BA.cancelDiscovery();
                registerBroadcast();
            }
        }, SCAN_DELAY);

        getBluetoothAdapter().startDiscovery();
        registerBroadcast();
    }

    public boolean connect(BluetoothDevice device) throws IOException {
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

        UUID uuid = UUID.fromString(SPP_UUID);

        BluetoothSocket socket = null;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Toast.makeText(context, "faild", Toast.LENGTH_SHORT).show();
        }
        try {
            socket.connect();
            this.bluetoothSocket = socket;

            BluetoothDevice b = null;
            if (socket.getRemoteDevice() != null) {
                b = socket.getRemoteDevice();
            }

        } catch (IOException e) {
            try {
                socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
            socket.connect();

            this.bluetoothSocket = socket;



    }
        if(socket.isConnected() == true){
            Toast.makeText(context,"has Conencted",Toast.LENGTH_SHORT).show();

            this.bluetoothSocket = socket;

            return true;
        }
        else{
            Toast.makeText(context,"Conencte faild",Toast.LENGTH_SHORT).show();

            return false;
        }
        /*Toast.makeText(context,"has Conencted",Toast.LENGTH_SHORT).show();*/
    }

    public BluetoothDevice getBluetoothDevice(int position) {
        return remoteDevice.get(position);
    }

    /*发送byte数组*/
    public void sendMessage(BluetoothSocket socket,byte[] message){
        if(socket != null) {
            if (socket.isConnected() == true) {
                OutputStream outStream = null;
                try {
                    outStream = socket.getOutputStream();
                } catch (IOException e) {
                    Toast.makeText(context, "Send failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                char light = 0X04;
                try {
                    outStream.write(message);
                } catch (IOException e) {
                    Toast.makeText(context, "Send failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Toast.makeText(context, "Send complete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "there's no connection", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(context, "there's no connection", Toast.LENGTH_SHORT).show();
        }
    }

    /*发送单个byte*/
    public void sendMessage(BluetoothSocket socket,byte message){
        if(socket != null) {
            if (socket.isConnected() == true) {
                OutputStream outStream = null;
                try {
                    outStream = socket.getOutputStream();
                } catch (IOException e) {
                    Toast.makeText(context, "Send failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                char light = 0X04;
                try {
                    outStream.write(message);
                } catch (IOException e) {
                    Toast.makeText(context, "Send failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Toast.makeText(context, "Send complete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "there's no connection", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(context, "there's no connection", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOpen(){
        return BA.isEnabled();
    }

    private void registerBroadcast() {
        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(mReceiver, filter);
    }

}




