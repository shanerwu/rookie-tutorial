package org.tutorial.service;

import java.util.List;

import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

public interface DeptService {

    DeptDO update(Integer deptno, String dname, String loc);

    List<DeptDO> getAll();

    DeptDO getOneDept(Integer deptno);

    List<EmpDO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

}
