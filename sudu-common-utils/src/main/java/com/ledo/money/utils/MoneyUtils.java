package com.ledo.money.utils;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Created by konghang on 2018/1/24.
 */
public class MoneyUtils {

    /**
     * 分转元
     *
     * @param fen
     */
    public static String fenToYuan(Integer fen){
        float r = fen==null?0.00f:fen/100.00f;
        double y = Math.round(r * 100.00) / 100.00;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(y);
    }

    /**
     * 分转元
     *
     * @param fen
     */
    public static String fenToYuan(Long fen){
        float r = fen==null?0.00f:fen/100.00f;
        double y = Math.round(r * 100.00) / 100.00;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(y);
    }

    /**
     * 分转元
     *
     * @param fen
     */
    public static Double fenToYuanDouble(Integer fen){
        float r = fen==null?0.00f:fen/100.00f;
        double y = Math.round(r * 100) / 100.00;
        return y;
    }

    /**
     * 分转元
     *
     * @param fen
     */
    public static Double fenToYuanDouble(Long fen){
        float r = fen==null?0.00f:fen/100.00f;
        double y = Math.round(r * 100) / 100.00;
        return y;
    }

    /**
     * 元转分
     *
     * @param y
     */
    public static long yuanToFen(String y){
        if(Objects.isNull(y) || "".equals(y.trim())){
            return 0;
        }
        Double x = Double.parseDouble(y);
        return  Math.round(x*100);
    }

    /**
     * 元转分
     *
     * @param y
     */
    public static int yuanToFenOfInt(String y){
        if(Objects.isNull(y) || "".equals(y.trim())){
            return 0;
        }
        Double x = Double.parseDouble(y);
        return  (int)Math.round(x*100);
    }

    /**
     * 元转分
     *
     * @param y
     */
    public static long yuanToFenOfLong(String y){
        if(Objects.isNull(y) || "".equals(y.trim())){
            return 0;
        }
        Double x = Double.parseDouble(y);
        return (long)Math.round(x*100);
    }

    /**
     * 元转分
     *
     * @param y
     */
    public static long yuanToFen(double y){
        return Math.round(y*100);
    }

    /**
     * 元转分
     *
     * @param y
     */
    public static int yuanToFenIntoInt(double y){
        return (int)Math.round(y*100);
    }

}
