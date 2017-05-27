package com.module.sample;

import com.cxfframe.server.module.ServiceBase;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * 私有模块测试
 *
 * @author CYX
 * @create 2017-05-25-23:10
 */
public class Sample extends ServiceBase {

    private static final Logger logger = Logger.getLogger(Sample.class);

    @Override
    public void loadConfig(HashMap<String, String> moduleConf) throws Exception {

        logger.info("调用Sample loadConfig : " + moduleConf);

    }

    @Override
    public void init() throws Exception {
        logger.info("调用Sample 的 init()");
    }

    @Override
    public String process(String information) throws Exception {

        logger.info("Sample process 方法");

        return "哈哈哈";
    }
}
