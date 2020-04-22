package org.tutorial.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpDAOImplTest {

    @Autowired
    private EmpDAO dao;

    @Test
    public void insert() {
        EmpVO empVO = new EmpVO();
        empVO.setEname("王小明1");
        empVO.setJob("manager");
        empVO.setHiredate(LocalDate.parse("2020-04-01"));
        empVO.setSal(new Double(50000));
        empVO.setComm(new Double(500));
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(40);
        empVO.setDeptVO(deptVO);
        dao.insert(empVO);
    }

    @Test
    public void update() {
        EmpVO empVO = new EmpVO();
        empVO.setEmpno(7001);
        empVO.setEname("王小明2");
        empVO.setJob("manager2");
        empVO.setHiredate(LocalDate.parse("2020-04-01"));
        empVO.setSal(new Double(20000));
        empVO.setComm(new Double(200));
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(40);
        empVO.setDeptVO(deptVO);
        dao.update(empVO);
    }

    @Test
    public void delete() {
        dao.delete(7014);
    }

    @Test
    public void findByPrimaryKey() {
        EmpVO empVO = dao.findByPrimaryKey(7001);
        System.out.print(empVO.getEmpno() + ",");
        System.out.print(empVO.getEname() + ",");
        System.out.print(empVO.getJob() + ",");
        System.out.print(empVO.getHiredate() + ",");
        System.out.print(empVO.getSal() + ",");
        System.out.print(empVO.getComm() + ",");
        System.out.print(empVO.getDeptVO().getDeptno() + ",");
        System.out.print(empVO.getDeptVO().getDname() + ",");
        System.out.println(empVO.getDeptVO().getLoc());
        System.out.println("---------------------");
    }

    @Test
    public void getAll() {
        List<EmpVO> list = dao.getAll();
        for (EmpVO aEmp : list) {
            System.out.print(aEmp.getEmpno() + ",");
            System.out.print(aEmp.getEname() + ",");
            System.out.print(aEmp.getJob() + ",");
            System.out.print(aEmp.getHiredate() + ",");
            System.out.print(aEmp.getSal() + ",");
            System.out.print(aEmp.getComm() + ",");
            System.out.print(aEmp.getDeptVO().getDeptno() + ",");
            System.out.print(aEmp.getDeptVO().getDname() + ",");
            System.out.print(aEmp.getDeptVO().getLoc());
            System.out.println();
        }
    }

}