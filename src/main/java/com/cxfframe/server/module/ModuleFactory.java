package com.cxfframe.server.module;

import com.cxfframe.server.load.LoadConfig;
import com.cxfframe.service.CXFFrameRelease;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * 模块工厂,负责创建模块对象
 *
 * @author CYX
 * @create 2017-05-25-11:00
 */
public class ModuleFactory {

    private static final Logger logger = CXFFrameRelease.logger;

    /**
     * 获取模块实例
     *
     * @param modeulName
     * @return
     */
    public static ServiceBase getModuleInstance(String moduleName) {

        LoadConfig loadConfig = LoadConfig.getInstance();

        ServiceBase serviceBase = null;

        // 实例化模块
        Object obj = null;
        try {
            // 获取模块对应的class路径
            String moduleClass = loadConfig.getModuleNameWithClass().get(moduleName);
            logger.info("module : " + moduleName + " , class : " + moduleClass);
            if (StringUtils.isEmpty(moduleClass)) {
                logger.info("module : " + moduleName + " , class is null");
                return null;
            }

            // 通过反射获取模块实例
            Class exampleClass = Class.forName(moduleClass);
            obj = exampleClass.newInstance();

            serviceBase = (ServiceBase) obj;

            // 加载模块并初始化

            // 根据模块名,取出模块对应的配置文件
            HashMap<String, String> moduleConfigNameWithInfo = loadConfig.getModulesConfigWithInfomation().get(moduleName);

            // 私有模块加载配置
            serviceBase.loadConfig(moduleConfigNameWithInfo);

            // 私有模块初始化
            serviceBase.init();

            logger.info("===== Load Configuration Initialize the private module is complete =====");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        return serviceBase;
    }

}
