package com.sally.fanguubao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sally.fanguubao.R;
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sally on 16/5/29.
 */
public class FenQiIphoneAdapter extends BaseAdapter {

    private List<FenQiIphoneProduct> mLists;
    private LayoutInflater mInflater;

    public FenQiIphoneAdapter(List<FenQiIphoneProduct> lists, Context context) {
        this.mLists = lists;
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
            convertView = mInflater.inflate(R.layout.item_iphone_list_view, null);
            vh.ivLogo = (ImageView) convertView.findViewById(R.id.id_iphone_item_logo);
            vh.tvName = (TextView) convertView.findViewById(R.id.id_iphone_item_name);
            vh.tvPrice = (TextView) convertView.findViewById(R.id.id_iphone_item_price);
            vh.tvFqPrice = (TextView) convertView.findViewById(R.id.id_iphone_item_fq_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FenQiIphoneProduct product = mLists.get(position);
        UrlImageViewHelper.setUrlDrawable(vh.ivLogo, product.getLogo().getUrl(), R.drawable.a);
        vh.tvName.setText(product.getName());
        vh.tvPrice.setText("全款 " + Constant.REN_MIN_BI + product.getPrice() + "");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String fePrice = decimalFormat.format(product.getPrice() / 12);
        vh.tvFqPrice.setText(Constant.REN_MIN_BI + fePrice + " * 12月");
        return convertView;
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvName;
        TextView tvPrice;
        TextView tvFqPrice;
    }
}
