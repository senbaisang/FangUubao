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
import com.sally.fanguubao.bean.FenQiJdProduct;
import com.sally.fanguubao.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiJdProductAdapter extends BaseAdapter {

    private List<FenQiJdProduct> mLists;
    private LayoutInflater mInflater;

    public FenQiJdProductAdapter(List<FenQiJdProduct> list, Context context) {
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
        ViewHolder vh = null;
        if(convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_fq_jd_list_view, null);
            vh.ivLogo = (ImageView) convertView.findViewById(R.id.id_item_fq_jd_logo);
            vh.tvName = (TextView) convertView.findViewById(R.id.id_item_fq_jd_name);
            vh.tvPrice = (TextView) convertView.findViewById(R.id.id_item_fq_jd_price);
            vh.tvFqPrice = (TextView) convertView.findViewById(R.id.id_item_fq_jd_fq_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FenQiJdProduct product = mLists.get(position);
        UrlImageViewHelper.setUrlDrawable(vh.ivLogo, product.getLogo(), R.mipmap.ic_launcher);
        vh.tvName.setText(product.getName());
        vh.tvPrice.setText(Constant.REN_MIN_BI + product.getPrice());
        vh.tvFqPrice.setText(Constant.REN_MIN_BI + new DecimalFormat("#.##").format(product.getPrice()) + "*12月");
        return convertView;
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvName;
        TextView tvPrice;
        TextView tvFqPrice;
    }
}
