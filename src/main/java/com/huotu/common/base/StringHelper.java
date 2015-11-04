/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huotu.common.base;

import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 字符处理类
 */
public class StringHelper {
    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 产生随机码
     * 1-9 A-Z 不含O
     * @param ran
     * @param xLen 长度
     * @return
     */
    public static String randomNo(Random ran, int xLen) {
        String[] char_array = new String[34];
        char_array[0] = "1";
        char_array[1] = "2";
        char_array[2] = "3";
        char_array[3] = "4";
        char_array[4] = "5";
        char_array[5] = "6";
        char_array[6] = "7";
        char_array[7] = "8";
        char_array[8] = "9";
        char_array[9] = "A";
        char_array[10] = "B";
        char_array[11] = "C";
        char_array[12] = "D";
        char_array[13] = "E";
        char_array[14] = "F";
        char_array[15] = "G";
        char_array[16] = "H";
        char_array[17] = "I";
        char_array[18] = "J";
        char_array[19] = "K";
        char_array[20] = "L";
        char_array[21] = "M";
        char_array[22] = "N";
        char_array[23] = "P";
        char_array[24] = "Q";
        char_array[25] = "R";
        char_array[26] = "S";
        char_array[27] = "T";
        char_array[28] = "W";
        char_array[29] = "U";
        char_array[30] = "V";
        char_array[31] = "X";
        char_array[32] = "Y";
        char_array[33] = "Z";

        String output = "";
        double tmp = 0;
        while (output.length() < xLen) {
            tmp = ran.nextFloat();
            output = output + char_array[(int) (tmp * 34)];
        }
        return output;
    }

    /**
     * 产生随机码
     * 1-9
     * @param ran
     * @param xLen
     * @return
     */
    public static String randomNum(Random ran, int xLen) {
        String[] char_array = new String[9];
        char_array[0] = "1";
        char_array[1] = "2";
        char_array[2] = "3";
        char_array[3] = "4";
        char_array[4] = "5";
        char_array[5] = "6";
        char_array[6] = "7";
        char_array[7] = "8";
        char_array[8] = "9";

        String output = "";
        double tmp = 0;
        while (output.length() < xLen) {
            tmp = ran.nextFloat();
            output = output + char_array[(int) (tmp * 9)];
        }
        return output;
    }
}
