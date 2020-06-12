package org.tutorial.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
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
        deptDO.setDeptno(10);
        empDO.setDeptDO(deptDO);
        dao.insert(empDO);
        assertTrue(true);
    }

    @Test
    public void update() {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(7002);
        empDO.setEname("王小明2");
        empDO.setJob("manager2");
        empDO.setHiredate(LocalDate.parse(("2020-04-01")));
        empDO.setSal(new Double(20000));
        empDO.setComm(new Double(200));
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(20);
        empDO.setDeptDO(deptDO);
        dao.update(empDO);
        assertTrue(true);
    }

    @Test
    public void delete() {
        dao.delete(7014);
        assertTrue(true);
    }

    @Test
    public void findByPrimaryKey() {
        EmpDO empDO = dao.findByPrimaryKey(7001);
        assertEquals(Integer.valueOf(7001), empDO.getEmpno());
        assertEquals("king", empDO.getEname());
        assertEquals("president", empDO.getJob());
    }

    @Test
    public void getAll() {
        List<EmpDO> list = dao.getAll();
        for (EmpDO empDO : list) {
            System.out.print(empDO.getEmpno() + ",");
            System.out.print(empDO.getEname() + ",");
            System.out.print(empDO.getJob() + ",");
            System.out.print(empDO.getHiredate() + ",");
            System.out.print(empDO.getSal() + ",");
            System.out.print(empDO.getComm() + ",");
            System.out.print(empDO.getDeptDO().getDeptno());
            System.out.println();
        }
    }

}