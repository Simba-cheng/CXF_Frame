package com.cxfframe.server.common;

/**
 * Exception错误信息
 *
 * @author CYX
 */
public class ExceptionInfoConstans {

    /**
     * moduleName和Params参数错误
     */
    public static final String MODULENAME_PARAMS_EXCEPTION = "[ErrorCode-01] Error information : Server receives the information , moduleName or params is null , please check it ";

    /**
     * moduleName找不到对应模块
     */
    public static final String MODULENAME_CLASS_EXCEPTION = "[ErrorCode-02] Error information : The server can not find the corresponding module";

    /**
     * 实例化模块错误
     */
    public static final String INSTANTIATE_MODULE_EXCEPTION = "[ErrorCode-03] Error information : Instantiate module error";

    /**
     * 服务端模块处理错误
     */
    public static final String SERVER_PROCESS_EXCEPTION = "[ErrorCode-04] Error information : Server module processing error";
}
