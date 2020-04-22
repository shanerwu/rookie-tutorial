package org.tutorial.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DeptDAOImplTest {

    @Autowired
    private DeptDAO dao;

    @Test
    public void insert() {
        DeptVO deptVO = new DeptVO();
        deptVO.setDname("製造部");
        deptVO.setLoc("美國洛杉磯");
        dao.insert(deptVO);
    }

    @Test
    public void update() {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(10);
        deptVO.setDname("財務部2");
        deptVO.setLoc("臺灣台北2");
        dao.update(deptVO);
    }

    @Test
    public void delete() {
        dao.delete(30);
    }

    @Test
    public void findByPrimaryKey() {
        DeptVO deptVO = dao.findByPrimaryKey(10);
        System.out.print(deptVO.getDeptno() + ",");
        System.out.print(deptVO.getDname() + ",");
        System.out.println(deptVO.getLoc());
        System.out.println("---------------------");
    }

    @Test
    public void getAll() {
        List<DeptVO> list = dao.getAll();
        for (DeptVO aDept : list) {
            System.out.print(aDept.getDeptno() + ",");
            System.out.print(aDept.getDname() + ",");
            System.out.println(aDept.getLoc() + ": ");
            for (EmpVO aEmp : aDept.getEmpVOs()) {
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
        DeptVO deptVO = new DeptVO();
        deptVO.setLoc("臺灣");
        deptVO.setDname("研發部");
        dao.findByCriteria(deptVO)
                .forEach(dept -> {
                    System.out.print(dept.getDeptno() + ",");
                    System.out.print(dept.getDname() + ",");
                    System.out.print(dept.getLoc());
                    System.out.println();
                });
    }

}