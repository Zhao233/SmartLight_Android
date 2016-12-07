package com.example.admin.test_bluetooth.Data;

/**
 * Created by admin on 2016/11/16.
 */

public class Data {
    private byte data;
    private byte[] datas;
    private byte[][] datass;

    /*构造方法*/
    public Data(byte Data){
        data = Data;
    }

    public Data(byte[] Datas){
        datas = Datas;
    }

    public Data(byte[][] Datass){
        datass = Datass;
    }

    public Data(){

    }

    /*set方法*/
    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public void setDatass(byte[][] data) {
        this.datass = data;
    }

    /*get方法*/
    public byte[] getDatas() {
        return datas;
    }

    public byte getData() {
        return data;
    }

    public byte[][] getDatass() {
        return datass;
    }
    public byte[] getDatass(int number){
        if(datass == null){
            return null;
        }
        else{
            byte[] temp = null;

            for(int i = 0; i < datass[number].length; i++){
                temp[i] = datass[number][i];
            }

            return temp;
        }
    }
}
