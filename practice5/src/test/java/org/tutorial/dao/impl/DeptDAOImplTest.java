package org.tutorial.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.config.TestConfig;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class DeptDAOImplTest {

    @Autowired
    private DeptDAO dao;

    @Test
    public void insert() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDname("製造部");
        deptDO.setLoc("美國洛杉磯");
        dao.insert(deptDO);
        assertTrue(true);
    }

    @Test
    public void update() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        deptDO.setDname("財務部2");
        deptDO.setLoc("臺灣台北2");
        dao.update(deptDO);
        assertTrue(true);
    }

    @Test
    public void delete() {
        dao.delete(30);
        assertTrue(true);
    }

    @Test
    public void findByPrimaryKey() {
        DeptDO deptDO = dao.findByPrimaryKey(20);
        assertEquals(Integer.valueOf(20), deptDO.getDeptno());
        assertEquals("研發部", deptDO.getDname());
        assertEquals("臺灣新竹", deptDO.getLoc());
    }

    @Test
    public void getAll() {
        List<DeptDO> list = dao.getAll();
        for (DeptDO deptDO : list) {
            System.out.print(deptDO.getDeptno() + ",");
            System.out.print(deptDO.getDname() + ",");
            System.out.print(deptDO.getLoc());
            System.out.println();
        }
    }

    @Test
    public void getEmpsByDeptno() {
        List<EmpDO> list = dao.getEmpsByDeptno(10);
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

    @Test
    public void findByCriteria() {
        DeptDO deptDO = new DeptDO();
        deptDO.setLoc("臺灣");
        deptDO.setDname("研發部");
        dao.findByCriteria(deptDO)
                .forEach(dept -> {
                    System.out.print(dept.getDeptno() + ",");
                    System.out.print(dept.getDname() + ",");
                    System.out.print(dept.getLoc());
                    System.out.println();
                });
    }

}