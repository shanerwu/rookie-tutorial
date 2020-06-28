package org.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.tutorial.model.entity.EmpDO;

public interface EmpService {

    EmpDO addEmp(EmpDO empDO);

    EmpDO updateEmp(EmpDO empDO);

    void deleteEmp(Integer empno);

    Optional<EmpDO> getOneEmp(Integer empno);

    List<EmpDO> getAll();

}
