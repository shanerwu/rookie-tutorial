package org.tutorial.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDAO dao;

    @Override
    public EmpDO addEmp(String ename, String job, LocalDate hiredate,
                        Double sal, Double comm, Integer deptno) {

        EmpDO empDO = new EmpDO();

        empDO.setEname(ename);
        empDO.setJob(job);
        empDO.setHiredate(hiredate);
        empDO.setSal(sal);
        empDO.setComm(comm);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(deptno);
        empDO.setDeptDO(deptDO);
        dao.insert(empDO);

        return empDO;
    }

    @Override
    public EmpDO updateEmp(Integer empno, String ename, String job,
                           LocalDate hiredate, Double sal, Double comm, Integer deptno) {

        EmpDO empDO = new EmpDO();

        empDO.setEmpno(empno);
        empDO.setEname(ename);
        empDO.setJob(job);
        empDO.setHiredate(hiredate);
        empDO.setSal(sal);
        empDO.setComm(comm);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(deptno);
        empDO.setDeptDO(deptDO);
        dao.update(empDO);

        return dao.findByPrimaryKey(empno);
    }

    @Override
    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    @Override
    public EmpDO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return dao.getAll();
    }

}
