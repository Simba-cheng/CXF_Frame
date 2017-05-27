package com.cxfframe.server.module;

import java.util.HashMap;

/**
 * 基类
 *
 * @author CYX
 * @create 2017-05-25-11:01
 */
public abstract class ServiceBase {

    /**
     * CXFFrame将模块对应的配置文件,传给对应私有模块
     *
     * @param moduleConf
     * @throws Exception
     */
    public abstract void loadConfig(HashMap<String, String> moduleConf) throws Exception;

    /**
     * 初始化
     *
     * @throws Exception
     */
    public abstract void init() throws Exception;

    /**
     * 模块主处理方法
     *
     * @param information
     * @return
     * @throws Exception
     */
    public abstract String process(String information) throws Exception;


}
