package org.tutorial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDAO dao;

    @Override
    public DeptDO update(Integer deptno, String dname, String loc) {
        DeptDO deptDO = new DeptDO();
        deptDO.setDeptno(deptno);
        deptDO.setDname(dname);
        deptDO.setLoc(loc);
        dao.update(deptDO);
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    public List<DeptDO> getAll() {
        return dao.getAll();
    }

    @Override
    public DeptDO getOneDept(Integer deptno) {
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        return dao.findByPrimaryKey(deptno).getEmpDOS();
    }

    @Override
    public void deleteDept(Integer deptno) {
        dao.delete(deptno);
    }

}
