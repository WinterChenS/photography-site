package com.winterchen.common.constants;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 3:08 下午
 * @description 常用静态常量
 **/
public class DefaultConstant {

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * 常用接口
     */
    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }

}
