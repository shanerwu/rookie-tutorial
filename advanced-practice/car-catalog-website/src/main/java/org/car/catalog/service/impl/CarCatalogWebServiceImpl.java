package org.car.catalog.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.car.catalog.domain.param.OrderCarParam;
import org.car.catalog.domain.result.Car;
import org.car.catalog.domain.result.OrderCarResult;
import org.car.catalog.domain.result.SearchCarResult;
import org.car.catalog.service.CarCatalogWebService;
import org.car.catalog.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@WebService(name = "CarCatalogWebService", serviceName = "CarCatalogWebService",
        portName = "CarCatalogWebService", targetNamespace = "http://service.catalog.car.org"
)
public class CarCatalogWebServiceImpl implements CarCatalogWebService {

    @Autowired
    private CarService carService;

    @Override
    @WebMethod  
    public SearchCarResult search(@WebParam(name = "keyword") String keyword,
                                  @WebParam(name = "pageNumber") Integer pageNumber,
                                  @WebParam(name = "pageSize") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Car> cars = carService.search(keyword, pageable).map(Car::new);
        SearchCarResult searchCarResult = new SearchCarResult();
        searchCarResult.setCars(cars.getContent());
        searchCarResult.setTotalSize((int) cars.getTotalElements());
        return searchCarResult;
    }

    @Override
    @WebMethod
    public OrderCarResult orderCar(@WebParam(name = "orderCarParam") OrderCarParam orderCarParam) {
        OrderCarResult orderCarResult = new OrderCarResult();
        try {
            boolean isSuccess = carService.updateCarAmount(orderCarParam.getId(), orderCarParam.getAmount());
            orderCarResult.setSuccess(isSuccess);
        } catch (Exception e) {
            e.printStackTrace();
            orderCarResult.setSuccess(false);
        }
        return orderCarResult;
    }
}
