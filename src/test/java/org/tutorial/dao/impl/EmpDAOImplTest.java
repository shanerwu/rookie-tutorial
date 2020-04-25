package org.tutorial.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.config.TestConfig;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class EmpDAOImplTest {

    @Autowired
    private EmpDAO dao;

    @Test
    public void insert() {
        EmpDO empDO = new EmpDO();
        empDO.setEname("王小明1");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse("2020-04-01"));
        empDO.setSal(new Double(50000));
        empDO.setComm(new Double(500));
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(40);
        empDO.setDeptDO(deptDO);
        dao.insert(empDO);
    }

    @Test
    public void update() {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(7001);
        empDO.setEname("王小明2");
        empDO.setJob("manager2");
        empDO.setHiredate(LocalDate.parse("2020-04-01"));
        empDO.setSal(new Double(20000));
        empDO.setComm(new Double(200));
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(40);
        empDO.setDeptDO(deptDO);
        dao.update(empDO);
    }

    @Test
    public void delete() {
        dao.delete(7014);
    }

    @Test
    public void findByPrimaryKey() {
        EmpDO empDO = dao.findByPrimaryKey(7001);
        System.out.print(empDO.getEmpno() + ",");
        System.out.print(empDO.getEname() + ",");
        System.out.print(empDO.getJob() + ",");
        System.out.print(empDO.getHiredate() + ",");
        System.out.print(empDO.getSal() + ",");
        System.out.print(empDO.getComm() + ",");
        System.out.print(empDO.getDeptDO().getDeptno() + ",");
        System.out.print(empDO.getDeptDO().getDname() + ",");
        System.out.println(empDO.getDeptDO().getLoc());
        System.out.println("---------------------");
    }

    @Test
    public void getAll() {
        List<EmpDO> list = dao.getAll();
        for (EmpDO aEmp : list) {
            System.out.print(aEmp.getEmpno() + ",");
            System.out.print(aEmp.getEname() + ",");
            System.out.print(aEmp.getJob() + ",");
            System.out.print(aEmp.getHiredate() + ",");
            System.out.print(aEmp.getSal() + ",");
            System.out.print(aEmp.getComm() + ",");
            System.out.print(aEmp.getDeptDO().getDeptno() + ",");
            System.out.print(aEmp.getDeptDO().getDname() + ",");
            System.out.print(aEmp.getDeptDO().getLoc());
            System.out.println();
        }
    }

}