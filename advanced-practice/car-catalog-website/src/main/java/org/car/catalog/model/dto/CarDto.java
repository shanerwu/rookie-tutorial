package org.car.catalog.model.dto;

import org.car.catalog.model.entiry.CarDO;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDto {
    private Integer id;
    private String model;
    private String make;
    private String photo;
    private String description;
    private Integer price;
    private Integer amount;

    public CarDto(CarDO carDO) {
        BeanUtils.copyProperties(carDO, this);
    }
}
