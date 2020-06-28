package org.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;

public interface DeptService {

    List<DeptDO> getAll();

    Page<DeptDO> getAllPages(Pageable pageable);

    Optional<DeptDO> getOneDept(Integer deptno);

    DeptDO update(DeptDO deptDO);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

}
