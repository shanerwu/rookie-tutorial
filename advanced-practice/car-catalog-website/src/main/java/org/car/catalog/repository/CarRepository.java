package org.car.catalog.repository;

import org.car.catalog.model.entity.CarDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarDO, Integer> {

    @Query(value = "SELECT c FROM CarDO c WHERE LOWER(c.model) LIKE LOWER(concat('%', :model, '%')) OR LOWER(c.make) LIKE LOWER(concat('%', :make, '%'))")
    Page<CarDO> findByModelLikeOrMakeLike(@Param("model") String model, @Param("make") String make, Pageable pageable);

//    Page<Car> findByModelContainingIgnoreCaseOrMakeContainingIgnoreCase(String model, String make, Pageable pageable);
}
