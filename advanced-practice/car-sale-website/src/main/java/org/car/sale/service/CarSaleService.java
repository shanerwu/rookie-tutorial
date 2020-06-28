package org.car.sale.service;

import org.car.catalog.service.OrderCarResult;
import org.car.catalog.service.SearchCarResult;

public interface CarSaleService {

    SearchCarResult findCar(String keyword, Integer pageNumber, Integer pageSize);

    OrderCarResult order(Integer id, Integer amount);
}
