package com.module.gis;

import com.cxfframe.server.module.ServiceBase;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * Gis服务
 *
 * @author CYX
 * @create 2017-05-25-23:30
 */
public class GisServer extends ServiceBase {

    private static final Logger logger = Logger.getLogger(GisServer.class);

    @Override
    public void loadConfig(HashMap<String, String> moduleConf) throws Exception {
        logger.info("GisServer : " + moduleConf);
    }

    @Override
    public void init() throws Exception {
        logger.info("GisServer");
    }

    @Override
    public String process(String information) throws Exception {
        logger.info("GisServer : " + information);
        return "GisServer";
    }
}
