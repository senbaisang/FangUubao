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
import com.sally.fanguubao.bean.FenQiLuxProductDetail;
import com.sally.fanguubao.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiLuxProductGvAdapter extends BaseAdapter {

    private List<FenQiLuxProductDetail> mLists;
    private LayoutInflater mInflater;

    public FenQiLuxProductGvAdapter(List<FenQiLuxProductDetail> list, Context context) {
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
            convertView = mInflater.inflate(R.layout.item_fq_lux_product_fg_grid_view, null);
            vh.ivLogo = (ImageView) convertView.findViewById(R.id.id_item_fq_lux_detail_logo);
            vh.tvName = (TextView) convertView.findViewById(R.id.id_item_fq_lux_detail_name);
            vh.tvPrice = (TextView) convertView.findViewById(R.id.id_item_fq_lux_detail_price);
            vh.tvFqPrice = (TextView) convertView.findViewById(R.id.id_item_fq_lux_detail_fq_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FenQiLuxProductDetail product = mLists.get(position);
        UrlImageViewHelper.setUrlDrawable(vh.ivLogo, product.getAttos_product_logos().get(0).getLogo().getUrl(), R.drawable.a);
        vh.tvName.setText(product.getName());
        vh.tvPrice.setText(Constant.REN_MIN_BI + product.getPrice());
        vh.tvFqPrice.setText(Constant.REN_MIN_BI + new DecimalFormat("#.##").format(Integer.valueOf(product.getPrice())) + "*12月");
        return convertView;
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvName;
        TextView tvPrice;
        TextView tvFqPrice;
    }
}
