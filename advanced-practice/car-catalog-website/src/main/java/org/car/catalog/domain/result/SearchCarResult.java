package org.car.catalog.domain.result;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCarResult {
    private List<Car> cars;
    private Integer totalSize;
}
