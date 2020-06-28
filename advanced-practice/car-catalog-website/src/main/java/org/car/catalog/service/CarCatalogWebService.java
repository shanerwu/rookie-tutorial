package org.car.catalog.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.car.catalog.domain.param.OrderCarParam;
import org.car.catalog.domain.result.OrderCarResult;
import org.car.catalog.domain.result.SearchCarResult;

@WebService
public interface CarCatalogWebService {

    @WebMethod
    SearchCarResult search(@WebParam(name = "keyword") String keyword,
                           @WebParam(name = "pageNumber") Integer pageNumber,
                           @WebParam(name = "pageSize") Integer pageSize);

    @WebMethod
    OrderCarResult orderCar(@WebParam(name = "orderCarParam") OrderCarParam orderCarParam);
}
