package com.cxfframe.server.inter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by CYX on 2017/5/24.
 */
@WebService
public interface QueryService {

    @WebMethod
    String queryServerInformation(@WebParam(name = "ModuleName") String moduleName, @WebParam(name = "params") String params);

}
