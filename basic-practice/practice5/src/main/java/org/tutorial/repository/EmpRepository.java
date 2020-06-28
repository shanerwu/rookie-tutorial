package org.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorial.model.entity.EmpDO;

@Repository
public interface EmpRepository extends JpaRepository<EmpDO, Integer> {
}
