package org.car.catalog.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.car.catalog.model.dto.CarDto;
import org.car.catalog.model.entity.CarDO;
import org.car.catalog.repository.CarRepository;
import org.car.catalog.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("carService")
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository repository;

    @Override
    public Page<CarDto> search(String keyword, Pageable pageable) {
        if (StringUtils.isBlank(keyword)) {
            return repository.findAll(pageable)
                    .map(CarDto::new);
        }
        return repository.findByModelLikeOrMakeLike(keyword, keyword, pageable)
                .map(CarDto::new);
    }

    @Override
    @Transactional
    public void insert(CarDto carDto) {
        CarDO carDO = new CarDO();
        BeanUtils.copyProperties(carDto, carDO);
        repository.save(carDO);
    }

    @Override
    @Transactional
    public boolean updateCarAmount(Integer id, Integer amount) {
        boolean isSuccess = false;
        Optional<CarDO> optional = repository.findById(id);
        if (optional.isPresent()) {
            CarDO carDO = optional.get();
            if (carDO.getAmount() - amount >= 0) {
                carDO.setAmount(carDO.getAmount() - amount);
                isSuccess = true;
            }
        }
        return isSuccess;
    }
}
