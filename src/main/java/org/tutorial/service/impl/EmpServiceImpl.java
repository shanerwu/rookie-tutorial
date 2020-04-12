package org.tutorial.service.impl;

import java.util.List;

import org.tutorial.dao.EmpDAO;
import org.tutorial.dao.impl.EmpDAOImpl;
import org.tutorial.model.EmpVO;
import org.tutorial.service.EmpService;

public class EmpServiceImpl implements EmpService {

    private EmpDAO dao;

    public EmpServiceImpl() {
        dao = new EmpDAOImpl();
    }

    @Override
    public EmpVO addEmp(String ename, String job, java.sql.Date hiredate,
                        Double sal, Double comm, Integer deptno) {

        EmpVO empVO = new EmpVO();

        empVO.setEname(ename);
        empVO.setJob(job);
        empVO.setHiredate(hiredate);
        empVO.setSal(sal);
        empVO.setComm(comm);
        empVO.setDeptno(deptno);
        dao.insert(empVO);

        return empVO;
    }

    @Override
    public EmpVO updateEmp(Integer empno, String ename, String job,
                           java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

        EmpVO empVO = new EmpVO();

        empVO.setEmpno(empno);
        empVO.setEname(ename);
        empVO.setJob(job);
        empVO.setHiredate(hiredate);
        empVO.setSal(sal);
        empVO.setComm(comm);
        empVO.setDeptno(deptno);
        dao.update(empVO);

        return dao.findByPrimaryKey(empno);
    }

    @Override
    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    @Override
    public EmpVO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpVO> getAll() {
        return dao.getAll();
    }

}
