package com.cxfframe.server.impl;

import com.cxfframe.server.common.ExceptionInfoConstans;
import com.cxfframe.server.inter.QueryService;
import com.cxfframe.server.load.LoadConfig;
import com.cxfframe.server.module.ModuleFactory;
import com.cxfframe.server.module.ServiceBase;
import com.cxfframe.service.CXFFrameRelease;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

/**
 * WebService接口实现
 *
 * @author CYX
 * @create 2017-05-24-21:15
 */
public class QueryServiceImpl implements QueryService {


    private final Logger logger = CXFFrameRelease.logger;


    public String queryServerInformation(String moduleName, String params) {

        // 参数为空,直接返回错误信息
        if (StringUtils.isEmpty(moduleName) & StringUtils.isEmpty(params)) {
            logger.info(ExceptionInfoConstans.MODULENAME_PARAMS_EXCEPTION);
            return ExceptionInfoConstans.MODULENAME_PARAMS_EXCEPTION;
        }

        // 根据模块名找class,找不到直接返回错误信息
        LoadConfig loadConfig = LoadConfig.getInstance();
        if (loadConfig.getModulesConfigWithInfomation().get(moduleName).isEmpty()) {
            logger.info("module name : " + moduleName + " can not find the corresponding module");
            return ExceptionInfoConstans.MODULENAME_CLASS_EXCEPTION;// moduleName找不到对应模块
        }


        logger.info("==================== The server begins processing ====================");
        logger.info("==================== The server begins processing ====================");


        logger.info("module name : " + moduleName + " , params : " + params);

        String responseResult = "";

        try {

            // 根据模块名获取模块实例,加载模块配置并初始化
            ServiceBase serviceBase = ModuleFactory.getModuleInstance(moduleName);

            //实例化模块失败,返回错误信息
            if (serviceBase == null) {
                logger.info(ExceptionInfoConstans.INSTANTIATE_MODULE_EXCEPTION);
                return ExceptionInfoConstans.INSTANTIATE_MODULE_EXCEPTION;
            }


            // 调用私有模块主方法
            responseResult = serviceBase.process(params);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ExceptionInfoConstans.SERVER_PROCESS_EXCEPTION;
        }

        logger.info("==================== The server is finished ====================");
        logger.info("==================== The server is finished ====================");

        return responseResult;
    }
}
