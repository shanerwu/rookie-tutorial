package org.tutorial.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.TutorialTestApplication;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.EmpService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TutorialTestApplication.class)
public class EmpServiceImplTest {

    @Autowired
    private EmpService service;

    @Test
    public void addEmp() {
        EmpDO empDO = new EmpDO();
        empDO.setEname("王小明1");
        empDO.setJob("manager");
        empDO.setHiredate(LocalDate.parse("2020-04-01"));
        empDO.setSal(50000d);
        empDO.setComm(500d);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        empDO.setDeptDO(deptDO);
        service.addEmp(empDO);
    }

    @Test
    public void updateEmp() {
        EmpDO empDO = new EmpDO();
        empDO.setEmpno(7002);
        empDO.setEname("王小明2");
        empDO.setJob("manager2");
        empDO.setHiredate(LocalDate.parse(("2020-04-01")));
        empDO.setSal(20000d);
        empDO.setComm(200d);
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(20);
        empDO.setDeptDO(deptDO);
        service.updateEmp(empDO);
        assertTrue(true);
    }

    @Test
    public void deleteEmp() {
        service.deleteEmp(7014);
        assertTrue(true);
    }

    @Test
    public void getOneEmp() {
        service.getOneEmp(7001).ifPresent(empDO -> {
            assertEquals(Integer.valueOf(7001), empDO.getEmpno());
            assertEquals("king", empDO.getEname());
            assertEquals("president", empDO.getJob());
        });
    }

    @Test
    public void getAll() {
        List<EmpDO> list = service.getAll();
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