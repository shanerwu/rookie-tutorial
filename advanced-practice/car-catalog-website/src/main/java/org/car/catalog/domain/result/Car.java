package org.car.catalog.domain.result;

import org.car.catalog.model.dto.CarDto;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Car {
    private Integer id;
    private String model;
    private String make;
    private String photo;
    private String description;
    private Integer price;
    private Integer amount;

    public Car(CarDto carDto) {
        BeanUtils.copyProperties(carDto, this);
    }
}
