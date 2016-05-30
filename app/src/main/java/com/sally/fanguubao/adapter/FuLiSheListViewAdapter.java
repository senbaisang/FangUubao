package com.sally.fanguubao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FuLiSheProduct;

import java.util.List;

/**
 * Created by sally on 16/5/27.
 */
public class FuLiSheListViewAdapter extends BaseAdapter {

    private List<FuLiSheProduct> mLists;
    private LayoutInflater mInflater;

    public FuLiSheListViewAdapter(List<FuLiSheProduct> list, Context context) {
        this.mLists = list;
        this.mInflater = LayoutInflater.from(context);
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
        ViewHolder vh;
        if(convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_fls, null);
            vh.tvName = (TextView) convertView.findViewById(R.id.id_fls_item_name);
            vh.tvDou = (TextView) convertView.findViewById(R.id.id_fls_item_dou);
            vh.ivLogo = (ImageView) convertView.findViewById(R.id.id_fls_item_iv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        FuLiSheProduct product = mLists.get(position);
        vh.tvName.setText(product.getName());
        vh.tvDou.setText(product.getScore() + "");
        UrlImageViewHelper.setUrlDrawable(vh.ivLogo, product.getLogo().getUrl(), R.drawable.b);

        return convertView;
    }

    public static class ViewHolder {
        TextView tvName;
        TextView tvDou;
        ImageView ivLogo;
    }
}
