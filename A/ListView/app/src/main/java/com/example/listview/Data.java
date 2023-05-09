package com.example.listview;

public class Data {
    private int id;
    private String content;

    public Data(){}

    public Data(int id,String content){
        this.id=id;
        this.content=content;
    }

    public int getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setContent(String content){
        this.content=content;
    }
}
