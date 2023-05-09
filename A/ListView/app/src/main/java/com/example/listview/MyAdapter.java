package com.example.listview;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {
    private Context mContext;

    private LinkedList<Data> mData;

    public MyAdapter(){
    }
    public MyAdapter(LinkedList<Data> mData,Context mContext){
        this.mData=mData;
        this.mContext=mContext;
    }
    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public Object getItem(int postion){
        return mData.get(postion);
    }
    @Override
    public long getItemId(int postion){
        return postion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder =null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder();
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img_icon.setImageResource(mData.get(position).getId());
        holder.txt_content.setText(mData.get(position).getContent());
        return convertView;
    }

    private class ViewHolder{
        ImageView img_icon;
        TextView txt_content;
    }

    public void add(Data data){
        if(mData==null){
            mData=new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    public void add(int position,Data data){
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(Data data) {
        if(mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if(mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

}

