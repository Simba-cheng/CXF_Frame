package com.cxfframe.server.util;

/**
 * 公共方法
 *
 * @author CYX
 * @create 2017-05-24-21:43
 */
public class CommonUtils {

    /**
     * 判断是否是windows操作系统(taskcenter使用)
     *
     * @return String
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

}
