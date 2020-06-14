package org.tutorial.service;

import java.util.List;

import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;

public interface DeptService {

    List<DeptDO> getAll();

    DeptDO getOneDept(Integer deptno);

    DeptDO update(DeptDO deptDO);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

}
