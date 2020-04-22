package org.tutorial.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;
import org.tutorial.service.EmpService;

public class EmpServiceImpl implements EmpService {

    private EmpDAO dao;

    public EmpServiceImpl() {
        //  dao = new EmpDAOImpl();
        // 將物件生成交由 Spring 管理
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("empDAOImpl", EmpDAO.class);
    }

    @Override
    public EmpVO addEmp(String ename, String job, LocalDate hiredate,
                        Double sal, Double comm, Integer deptno) {

        EmpVO empVO = new EmpVO();

        empVO.setEname(ename);
        empVO.setJob(job);
        empVO.setHiredate(hiredate);
        empVO.setSal(sal);
        empVO.setComm(comm);
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptno);
        empVO.setDeptVO(deptVO);
        dao.insert(empVO);

        return empVO;
    }

    @Override
    public EmpVO updateEmp(Integer empno, String ename, String job,
                           LocalDate hiredate, Double sal, Double comm, Integer deptno) {

        EmpVO empVO = new EmpVO();

        empVO.setEmpno(empno);
        empVO.setEname(ename);
        empVO.setJob(job);
        empVO.setHiredate(hiredate);
        empVO.setSal(sal);
        empVO.setComm(comm);
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptno);
        empVO.setDeptVO(deptVO);
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
