package com.cxfframe.server.common;

/**
 * Exception错误信息
 * 
 * @author CYX
 *
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

}
