package com.example.admin.smartlight.Controller_Data.List;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.admin.smartlight.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by admin on 2016/9/8.
 */

public class SetList {
    private ListView listView;
    private SimpleAdapter adapter;
    private Context context;

    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    String[] from = new String[]{"Names", "Address"};
    int[] to = new int[]{R.id.Name, R.id.Address};

    /*structure method*/
    public SetList(ListView newListView, Context newContext) {
        this.listView = newListView;
        this.context = newContext;
    }

    public SetList(ListView newListView, Context newContext, ArrayList<String> names, ArrayList<String> address) {
        this.listView = newListView;
        this.context = newContext;

        this.addData(names, address);
    }


    /*setter and getter*/
    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public SimpleAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SimpleAdapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<HashMap<String, String>> getList() {
        return list;
    }

    public void setList(ArrayList<HashMap<String, String>> list) {
        this.list = list;
    }

    public String[] getFrom() {
        return from;
    }

    public void setFrom(String[] from) {
        this.from = from;
    }

    public int[] getTo() {
        return to;
    }

    public void setTo(int[] to) {
        this.to = to;
    }

    public ArrayList<HashMap<String, String>> getData() {
        return list;
    }


    /*add the name of the wifi to the ListView*/
    public void addData(String name_Wifi) { // if judge = 0, do not set the adapter if = 1, set the adapter
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("value", name_Wifi);

        list.add(map);

        setAdapter();
    } // add the name one by one and put this list to the adapter

    public void addData(ArrayList<String> names, ArrayList<String> address) {// if judge = 0, do not set the adapter if = 1, set the adapter
        for (int i = 0; i < names.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Names", names.get(i));
            map.put("Address", address.get(i));
            /*Toast.makeText(context,"gg"+ArrayList.get(i),Toast.LENGTH_SHORT).show();*/
            list.add(map);
        }
        setAdapter();
    } // add the name as a list and put this list to the adapter

    public void setAdapter() {
        adapter = new SimpleAdapter(context, getData(), R.layout.item_show_connnection, from, to);
        listView.setAdapter(adapter);
    }
}
