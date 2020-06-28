package org.tutorial.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.TutorialTestApplication;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import org.tutorial.service.DeptService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TutorialTestApplication.class)
public class DeptServiceImplTest {

    @Autowired
    private DeptService service;

    @Test
    public void getAllPagesAndSort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "deptno");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<DeptDO> deptDOs = service.getAllPages(pageable);
        for (DeptDO deptDO : deptDOs) {
            System.out.println(deptDO.getDeptno());
        }
    }

    @Test
    public void getAllPages() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<DeptDO> deptDOs = service.getAllPages(pageable);
        for (DeptDO deptDO : deptDOs) {
            System.out.println(deptDO.getDeptno());
        }
    }

    @Test
    public void getAll() {
        List<DeptDO> list = service.getAll();
        for (DeptDO deptDO : list) {
            System.out.print(deptDO.getDeptno() + ",");
            System.out.print(deptDO.getDname() + ",");
            System.out.print(deptDO.getLoc());
            System.out.println();
        }
    }

    @Test
    public void getOneDept() {
        service.getOneDept(20).ifPresent(deptDO -> {
            assertEquals(Integer.valueOf(20), deptDO.getDeptno());
            assertEquals("研發部", deptDO.getDname());
            assertEquals("臺灣新竹", deptDO.getLoc());
        });
    }

    @Test
    public void update() {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(10);
        deptDO.setDname("財務部2");
        deptDO.setLoc("臺灣台北2");
        DeptDO updatedDeptDO = service.update(deptDO);
        assertEquals("財務部2", updatedDeptDO.getDname());
        assertEquals("臺灣台北2", updatedDeptDO.getLoc());
    }

    @Test
    public void getEmpsByDeptno() {
        List<EmpDO> list = service.getEmpsByDeptno(10);
        list.forEach(empDO -> {
            System.out.print(empDO.getEmpno() + ",");
            System.out.print(empDO.getEname() + ",");
            System.out.print(empDO.getJob() + ",");
            System.out.print(empDO.getHiredate() + ",");
            System.out.print(empDO.getSal() + ",");
            System.out.print(empDO.getComm() + ",");
            System.out.print(empDO.getDeptDO().getDeptno());
            System.out.println();
        });
    }

    @Test
    public void deleteDept() {
        service.deleteDept(30);
        assertTrue(true);
    }

}