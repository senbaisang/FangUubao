package com.sally.fanguubao.util;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/5/30.
 */
public class XmlPullParseUtil {

    /**
     * 解析商品的detail字段值，里面都是图片的url
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static List<ProductImage> parse(String str) throws Exception {
        List<ProductImage> lists = null;
        ProductImage imageUrl;

        XmlPullParserFactory xmlPullParseFactory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParse = xmlPullParseFactory.newPullParser();
        xmlPullParse.setInput(new StringReader(str));

        int eventType = xmlPullParse.getEventType();

        while (eventType != xmlPullParse.END_DOCUMENT) {
            String nodeName = xmlPullParse.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    lists = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("img".equals(nodeName)) {
                        imageUrl = new ProductImage();
                        imageUrl.setSrc(xmlPullParse.getAttributeValue(1));
                        lists.add(imageUrl);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("p".equals(nodeName)) {
                        imageUrl = null;
                    }
                    break;
            }
            eventType = xmlPullParse.next();
        }

        return lists;
    }
}
