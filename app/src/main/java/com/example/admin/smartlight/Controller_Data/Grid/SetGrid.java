package com.example.admin.smartlight.Controller_Data.Grid;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.admin.smartlight.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2016/11/21.
 */

public class SetGrid {
    GridView                                 gridView;
    SimpleAdapter                            adapter;
    ArrayList<HashMap<String,Object>>        list;
    HashMap map;

    int[] to ={
            R.id.image_mould,
            R.id.name_mould
    };

    String[] from = {
            "PictureResource",
            "Name"
    };

    String[] number = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
    };

    Integer src[] = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    Context context;

    public SetGrid(Context context, GridView gridView){
        this.context = context;
        this.gridView = gridView;
        list = new ArrayList<>();

        show();
    }


    public List<HashMap<String,Object>> getDataSource(){
        return list;
    }

    public boolean addData(Object picture, String name){// add a single picture and name
        map = new HashMap<String, Object>();
        map.put("PictureResource", picture);
        map.put("Name", name);

        list.add(map);
        return true;
    }
    public boolean addData(Object[] picture, String[] name){// add a string of pictures and names
        if(picture.length != name.length){
            Log.i("添加失败", "图片数量与名字数量不匹配");
            return false;
        }

        for(int i = 0; i < picture.length; i++){
            addData(picture[i], name[i]);
        }

        return true;
    }

    public SimpleAdapter setAdapter(){
        addData(src, number);
        adapter = new SimpleAdapter(context,getDataSource(),R.layout.chose_moudle_item,from,to);
        return adapter;
    }

    public void show(){ //show the gridview
        setAdapter();
        gridView.setAdapter(adapter);
    }

    public void setPictureSrc(int position, int resource){/*待完成*/
        ImageView image = (ImageView) gridView.getItemAtPosition(position);

        image.setImageResource(resource);
    }

}
