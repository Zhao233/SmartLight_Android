package com.example.admin.test_bluetooth.Bluetooth_Controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 2016/9/10.
 */
public class BlueTooth {
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;

    public BluetoothSocket getSocket() {
        return bluetoothSocket;
    }



    private Context context;

    public BlueTooth(Context newContext) {
        BA = BluetoothAdapter.getDefaultAdapter();

        pairedDevices = BA.getBondedDevices();

        this.context = newContext;

        /*AcceptThread a = new AcceptThread();
        a.start();*/

    }

    public BluetoothAdapter getBluetoothAdapter() {
        return BA;
    }

    public Set<BluetoothDevice> getPairedDevices() {
        return pairedDevices;
    }

    /*Turn on the bluetooth device*/
    public Intent On() {
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        Toast.makeText(context, "BlueTooth TurnOn", Toast.LENGTH_SHORT).show();
        return turnOn;
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
    public ArrayList<String> getListOfBluetoothDevice() {
        ArrayList<String> list = new ArrayList<String>();

        for (BluetoothDevice btd : pairedDevices) {
            list.add(btd.getName());
        }

        return list;
    }

    public void connect(BluetoothDevice device) throws IOException {
        getBluetoothAdapter().cancelDiscovery();
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
                Toast.makeText(context, b.getName(), Toast.LENGTH_SHORT).show();
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
            if(socket.isConnected() == true){
                Toast.makeText(context,"has Conencted",Toast.LENGTH_SHORT).show();

                 this.bluetoothSocket = socket;
            }
            else{
                Toast.makeText(context,"Conencte faild",Toast.LENGTH_SHORT).show();
            }


        }

        /*Toast.makeText(context,"has Conencted",Toast.LENGTH_SHORT).show();*/
    }

    public BluetoothDevice getBluetoothDevice(int position) {
        int i = 0;
        BluetoothDevice bl = null;

        for (BluetoothDevice bld : pairedDevices) {
            bl = bld;
            if (position == i) {
                break;
            }
            i++;
        }

        return bl;
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
}




