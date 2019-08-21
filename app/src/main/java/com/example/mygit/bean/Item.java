package com.example.mygit.bean;

public class Item {
    public int type;
    public String title;
    public int imageId;;
    public interface TYPE{
        int TYPE_GRID_THREE=0x01;
        int TYPE_GRID_TWO=0X02;
        int  TYPE_LIST=0X03;
        int  TYPE_TITLE=0X04;
    }

}
