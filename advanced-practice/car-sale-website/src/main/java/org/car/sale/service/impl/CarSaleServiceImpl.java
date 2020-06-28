package org.car.sale.service.impl;

import org.car.catalog.service.CarCatalogWebService;
import org.car.catalog.service.OrderCarParam;
import org.car.catalog.service.OrderCarResult;
import org.car.catalog.service.SearchCarResult;
import org.car.sale.service.CarSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carSaleService")
public class CarSaleServiceImpl implements CarSaleService {

    @Autowired
    private CarCatalogWebService carCatalogWebService;

    @Override
    public SearchCarResult findCar(String keyword, Integer pageNumber, Integer pageSize) {
        return carCatalogWebService.search(keyword, pageNumber, pageSize);
    }

    @Override
    public OrderCarResult order(Integer id, Integer amount) {
        OrderCarParam param = new OrderCarParam();
        param.setId(id);
        param.setAmount(amount);
        return carCatalogWebService.orderCar(param);
    }

}
