package org.car.catalog.service;

import org.car.catalog.model.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

    Page<CarDto> search(String keyword, Pageable pageable);

    void insert(CarDto car);

    boolean updateCarAmount(Integer id, Integer amount);
}
