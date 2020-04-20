package org.tutorial.service;

import java.util.List;

import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;

public interface DeptService {

    DeptVO update(Integer deptno, String dname, String loc);

    List<DeptVO> getAll();

    DeptVO getOneDept(Integer deptno);

    List<EmpVO> getEmpsByDeptno(Integer deptno);

    void deleteDept(Integer deptno);

}
