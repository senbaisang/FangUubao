package com.sally.fanguubao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiButton;

import java.util.List;

/**
 * Created by sally on 16/5/27.
 */
public class FenQiGridViewAdapter extends BaseAdapter {

    private List<FenQiButton> mLists;
    private LayoutInflater mInflater;

    public FenQiGridViewAdapter(List<FenQiButton> list, Context context) {
        this.mLists = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_fq, parent, false);
            vh.imageView = (ImageView) convertView.findViewById(R.id.id_fq_item_iv);
            vh.textView = (TextView) convertView.findViewById(R.id.id_fq_item_tv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.imageView.setImageResource(mLists.get(position).getImage());
        vh.textView.setText(mLists.get(position).getTitle());
        return convertView;
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
