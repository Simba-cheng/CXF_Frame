package com.cxfframe.server.common;

import com.cxfframe.server.util.CommonUtils;

/**
 * 常量
 *
 * @author CYX
 * @create 2017-05-24-21:41
 */
public class CommConstans {

    //判断相对路径根路径
    static {
        if (CommonUtils.isWindowsOS()) {
            SYSTEN_ROOT_PATH = "./";
        } else {
            SYSTEN_ROOT_PATH = "../";
        }
    }

    /**
     * 相对路径根路径
     * Windows(./) Linux(../)
     */
    public static String SYSTEN_ROOT_PATH;

    /**
     * CXFFrame配置文件路径
     */
    public static final String CXFFRAME_CONF_PATH = SYSTEN_ROOT_PATH + "conf/cxfframe_conf.properties";

    /**
     * CXFFrame_log4j配置文件路径
     */
    public static final String CXFFRAME_LOG4J_CONF_PATH = SYSTEN_ROOT_PATH + "conf/log4j.properties";

    /**
     * modules目录的路径
     */
    public static final String MODULES_DIR_PATH = SYSTEN_ROOT_PATH + "modules";

    /**
     * Modules.xml配置文件路径
     */
    public static final String MODULES_XML_PATH = SYSTEN_ROOT_PATH + "conf/Modules.xml";
}
