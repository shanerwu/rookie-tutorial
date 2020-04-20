package org.tutorial.dao.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;

public class DeptDAOImplTest {

    private DeptDAO dao;

    @Before
    public void init() {
        dao = new DeptDAOImpl();
    }

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
            System.out.print(aDept.getLoc());
            System.out.println();
        }
    }

    @Test
    public void getEmpsByDeptno() {
        List<EmpVO> list = dao.getEmpsByDeptno(20);
        for (EmpVO aEmp : list) {
            System.out.print(aEmp.getEmpno() + ",");
            System.out.print(aEmp.getEname() + ",");
            System.out.print(aEmp.getJob() + ",");
            System.out.print(aEmp.getHiredate() + ",");
            System.out.print(aEmp.getSal() + ",");
            System.out.print(aEmp.getComm() + ",");
            System.out.print(aEmp.getDeptno());
            System.out.println();
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