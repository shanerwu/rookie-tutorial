package org.car.sale.service.impl;

import org.car.catalog.service.CarCatalogWebService;
import org.car.catalog.service.SearchCarResult;
import org.car.sale.CarSaleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = CarSaleApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CarSaleServiceImplTest {

    @Autowired
    private CarCatalogWebService carCatalogWebService;

    @Test
    public void test() {
        SearchCarResult result = carCatalogWebService.search("", 0, 3);
        result.getCars().forEach(car -> System.out.println(car.getModel()));
    }

}