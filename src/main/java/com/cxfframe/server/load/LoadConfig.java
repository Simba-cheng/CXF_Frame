package com.cxfframe.server.load;

import com.cxfframe.server.common.CommConstans;
import com.cxfframe.server.common.KeyConstans;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * 加载配置
 *
 * @author CYX
 * @create 2017-05-24-21:39
 */
public class LoadConfig {

    private static final Logger logger = Logger.getLogger(LoadConfig.class);

    //单例
    private static class LoadConfigHolder {
        private static final LoadConfig INSTANCE = new LoadConfig();
    }

    public static final LoadConfig getInstance() {
        return LoadConfigHolder.INSTANCE;
    }


    private String cxfFrame_IP;
    private String cxfFrame_PORT;
    private String cxfFrame_ServiceName;

    /**
     * 模块名和class全路径的映射
     */
    private HashMap<String, String> moduleNameWithClass = new HashMap<String, String>(20);

    /**
     * 存放私有模块的配置信息
     * 模块名-(配置文件名-配置文件内容)
     */
    private HashMap<String, HashMap<String, String>> modulesConfigWithInfomation = new HashMap<String, HashMap<String, String>>(20);

    /**
     * CXFFrame初始化
     *
     * @throws Exception
     */
    public void init() throws Exception {

        // 加载logger
        loadLogger();

        // 加载配置文件
        loadCXFFrameCofig();

        // 加载所有模块的私有配置
        loadModulesCofig();

        // 加载modules.xml
        loadModulesXML();

    }

    /**
     * 加载logger 支持动态改变log4j配置(类似于热部署)
     *
     * @throws Exception
     */
    private void loadLogger() throws Exception {
        PropertyConfigurator.configure(CommConstans.CXFFRAME_LOG4J_CONF_PATH);
        PropertyConfigurator.configureAndWatch(CommConstans.CXFFRAME_LOG4J_CONF_PATH, 1000);
    }

    /**
     * 加载CXFFrame配置文件
     *
     * @throws Exception
     */
    private void loadCXFFrameCofig() throws Exception {
        Properties pro = new Properties();
        pro.load(new FileInputStream(new File(CommConstans.CXFFRAME_CONF_PATH)));

        // 默认获取本地IP
        cxfFrame_IP = InetAddress.getLocalHost().getHostAddress();
        cxfFrame_PORT = pro.getProperty(KeyConstans.CXFFRAME_PORT);
        cxfFrame_ServiceName = pro.getProperty(KeyConstans.CXFFRAME_RELEASE_NAME);

        logger.debug("cxfFrame_IP : " + cxfFrame_IP);
        logger.debug("cxfFrame_PORT : " + cxfFrame_PORT);
        logger.debug("cxfFrame_ServiceName : " + cxfFrame_ServiceName);

    }

    /**
     * 加载modules.xml
     *
     * @throws Exception
     */
    private void loadModulesXML() throws Exception {

        String modulesResult = FileUtils.readFileToString(new File(CommConstans.MODULES_XML_PATH), "UTF-8");

        // 解析modules.xml
        handleModulesXML(modulesResult);

    }

    /**
     * 解析modules.xml
     *
     * @param modulesResult
     * @throws Exception
     */
    private void handleModulesXML(String modulesResult) throws Exception {

        Document document = DocumentHelper.parseText(modulesResult);
        Element rootElement = document.getRootElement();

        // 获取modules节点下所有的module节点
        List<Element> modules = rootElement.elements("module");

        for (Element module : modules) {
            String moduleName = module.attributeValue("name");// 获取name的属性值
            String moduleClass = module.attributeValue("class");// 获取class的属性值

            moduleNameWithClass.put(moduleName, moduleClass);

        }

    }

    /**
     * 加载modules目录下所有模块的私有配置
     *
     * @throws Exception
     */
    private void loadModulesCofig() throws Exception {

        // modules目录下,所有的子模块
        File[] moduleDirs = new File(CommConstans.MODULES_DIR_PATH).listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File moduleDir : moduleDirs) {

            // 加载单个私有模块的配置信息
            loadPrivatelyOwnedModuleConf(moduleDir);

        }

    }

    /**
     * 加载单个私有模块的配置
     */
    private void loadPrivatelyOwnedModuleConf(File moduleDir) throws Exception {

        String moduleName = moduleDir.getName();// 模块名

        // 获取私有模块conf目录下所有配置文件
        File[] configFiles = new File(moduleDir.getPath() + "/conf").listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });

        // 循环读取conf目录下的配置文件
        HashMap<String, String> configFileNameWithinfo = new HashMap<String, String>();

        if (null != configFiles) {

            for (File configFile : configFiles) {

                //私有模块的log4j配置文件,存储文件路径,不读取
                if (configFile.getName().contains("log4j")) {

                    String configFileName = configFile.getName();
                    String log4jFilePath = configFile.getPath();//模块私有log配置文件的相对路径

                    configFileNameWithinfo.put(configFileName, log4jFilePath);

                } else {
                    String configFileName = configFile.getName();// 文件名
                    String configInformation = FileUtils.readFileToString(configFile, "UTF-8");// 文件内容

                    // 文件名-文件内容,存入map
                    configFileNameWithinfo.put(configFileName, configInformation);
                }


            }

        } else {

            logger.info("module Name : " + moduleName + " , configFiles is null");

        }

        // 模块名-(配置文件名-配置文件内容)
        modulesConfigWithInfomation.put(moduleName, configFileNameWithinfo);

        logger.info("All modules private configuration" + modulesConfigWithInfomation);

    }

    public String getCxfFrame_IP() {
        return cxfFrame_IP;
    }

    public String getCxfFrame_PORT() {
        return cxfFrame_PORT;
    }

    public String getCxfFrame_ServiceName() {
        return cxfFrame_ServiceName;
    }

    public HashMap<String, String> getModuleNameWithClass() {
        return moduleNameWithClass;
    }

    public HashMap<String, HashMap<String, String>> getModulesConfigWithInfomation() {
        return modulesConfigWithInfomation;
    }
}
