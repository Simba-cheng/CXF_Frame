package com.cxfframe.service;

import com.cxfframe.server.impl.QueryServiceImpl;
import com.cxfframe.server.load.LoadConfig;
import org.apache.log4j.Logger;

import javax.xml.ws.Endpoint;

/**
 * CXFFrame发布主程序
 *
 * @author CYX
 * @create 2017-05-24-21:22
 */
public class CXFFrameRelease {

	private static final Logger logger = Logger.getLogger(CXFFrameRelease.class);

	public static void main(String[] args) {

		LoadConfig loadConfig = null;

		// 加载配置文件,出现异常,直接退出程序
		try {

			loadConfig = LoadConfig.getInstance();
			loadConfig.init();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(1);
		}

		// 发布CXF服务(WebService)
		try {

			String address = "http://" + loadConfig.getCxfFrame_IP() + ":" + loadConfig.getCxfFrame_PORT() + "/" + loadConfig.getCxfFrame_ServiceName() + "?wsdl";

			// CXFFrame主方法
			Endpoint.publish(address, new QueryServiceImpl());
			logger.info("CXFFrame release is success , address : " + address);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
