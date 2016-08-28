package com.sally.fanguubao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sally.fanguubao.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sally on 8/26/16.
 */
public class Utilities {

    /**
     * 打电话
     * @param context
     */
    public static void phoneCall(Context context) {
        Intent intent1 = new Intent(Intent.ACTION_DIAL);
        intent1.setData(Uri.parse("tel:" + Constant.KE_FU_DIAN_HUA));
        context.startActivity(intent1);
    }

    /**
     * code: "Ti.App.util.show_window(Ti.App.code_url + "/code/decoration_detail.js",
     * { title: "装修699套餐", product_name: "装修699套餐", common_application: true, product_id: 55,
     * application_tp: "decoration" });",
     *
     * 分期 － 精品推荐 － 装修传递的code参数是一段代码，这里只是想要product_id这个字段
     * @param string
     * @return
     */
    public static Map<String, String> decorationInfo(String string) {
        int start = string.indexOf("{");
        int end = string.indexOf("}");
        String str = string.substring(start + 1, end);
        String[] split = str.split(",");
        Map<String, String> map = new HashMap<>();
        for(String s : split) {
            map.put(s.split(":")[0], s.split(":")[1]);
        }
        return map;
    }

    /**
     * tusi
     * @param context
     * @param string
     */
    public static void showMsg(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    /**
     * dialog， listview展示单行数据
     * @param context
     * @param tv
     * @param mDatas
     * @param title
     */
    public static void showDialog(Context context, final TextView tv, final String[] mDatas, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setNegativeButton("cancel", null);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.item_dialog_listview, null);
        ListView listView = (ListView) dialogView.findViewById(R.id.id_dialog_list_view);
        listView.setAdapter(new ArrayAdapter<String>(context, R.layout.item_dialog_textview, mDatas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(mDatas[position]);
                dialog.dismiss();
            }
        });
        dialog.setTitle(title);
        dialog.setView(dialogView);
        dialog.show();
    }
}
