package org.tutorial.service.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.DeptService;

public class DeptServiceImpl implements DeptService {

    private DeptDAO dao;

    public DeptServiceImpl() {
        // 將 Dao 物件生成交由 Spring 管理
        // dao = new DeptDAOImpl();

        // 此種取得 Spring Bean 的方式為暫時測試用
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("deptDAOImpl", DeptDAO.class);
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
    public DeptDO update(DeptDO deptDO) {
        dao.update(deptDO);
        return dao.findByPrimaryKey(deptDO.getDeptno());
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        return dao.getEmpsByDeptno(deptno);
    }

    @Override
    public void deleteDept(Integer deptno) {
        dao.delete(deptno);
    }

}
