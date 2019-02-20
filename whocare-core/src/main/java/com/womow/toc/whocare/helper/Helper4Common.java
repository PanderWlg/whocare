package com.womow.toc.whocare.helper;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * 没有归档的 都在这里
 *
 * @author cxy    2015-06-15
 */
public class Helper4Common {

    /**
     * 获得32位的uuid
     *
     * @author cxy    2015-06-15
     */
    public static String getUuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得指定位数的随即字符串（数字和字符串）
     *
     * @param length 获得多少为的随即串
     * @param type   s:纯字符，n:纯数字，ns:字符数字混合
     * @return
     * @author CXY 2015-07-30
     */
    public static String getRandomString(int length, String type) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String base1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String base2 = "0123456789";
        if ("s".equals(type)) {
            base = base1;
        } else if ("n".equals(type)) {
            base = base2;
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 去 html标签
     *
     * @param str
     * @return
     */
    public static String HtmlToText(String str) {
        String sRet = "";
        str = str.replaceAll("\r", "").replaceAll("\n", "");
        sRet = str.replaceAll("</?[^>]+>", "");
        return sRet;
    }

    /**
     * list 是不是为空
     *
     * @param l list对象
     * @return 不为空：true，为空：false
     */
    public static boolean isListNotEmpty(List l) {
        return Helper4Common.isCollectionNotEmpty(l);
    }

    /**
     * 集合是否非空 是不是为空
     *
     * @param l list对象
     * @return 不为空：true，为空：false
     */
    public static boolean isCollectionNotEmpty(Collection l) {
        if (l != null && l.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isCollectionEmpty(Collection l) {
        if (l != null && l.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 将,分割的字符串变成sql in 的格式
     */
    public static String toInSqlStr(String str) {
        return "'" + str.replaceAll(",", "','") + "'";
    }

}
