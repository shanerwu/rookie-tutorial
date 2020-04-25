package org.tutorial.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.config.TestConfig;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;

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
    }

    @Test
    public void update() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        deptDO.setDname("財務部2");
        deptDO.setLoc("臺灣台北2");
        dao.update(deptDO);
    }

    @Test
    public void delete() {
        dao.delete(30);
    }

    @Test
    public void findByPrimaryKey() {
        DeptDO deptDO = dao.findByPrimaryKey(10);
        System.out.print(deptDO.getDeptno() + ",");
        System.out.print(deptDO.getDname() + ",");
        System.out.println(deptDO.getLoc());
        System.out.println("---------------------");
    }

    @Test
    public void getAll() {
        List<DeptDO> list = dao.getAll();
        for (DeptDO aDept : list) {
            System.out.print(aDept.getDeptno() + ",");
            System.out.print(aDept.getDname() + ",");
            System.out.println(aDept.getLoc() + ": ");
            for (EmpDO aEmp : aDept.getEmpDOS()) {
                System.out.print(aEmp.getEmpno() + ",");
                System.out.print(aEmp.getEname() + ",");
                System.out.print(aEmp.getJob() + ",");
                System.out.print(aEmp.getHiredate() + ",");
                System.out.print(aEmp.getSal() + ",");
                System.out.println(aEmp.getComm());
            }
            System.out.println("---------------------");
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