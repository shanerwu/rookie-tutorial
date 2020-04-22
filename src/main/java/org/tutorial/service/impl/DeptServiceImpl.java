package org.tutorial.service.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;
import org.tutorial.service.DeptService;

public class DeptServiceImpl implements DeptService {

    private DeptDAO dao;

    public DeptServiceImpl() {
        // dao = new DeptDAOImpl();
        // 將物件生成交由 Spring 管理
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("deptDAOImpl", DeptDAO.class);
    }

    @Override
    public DeptVO update(Integer deptno, String dname, String loc) {
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(deptno);
        deptVO.setDname(dname);
        deptVO.setLoc(loc);
        dao.update(deptVO);
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    public List<DeptVO> getAll() {
        return dao.getAll();
    }

    @Override
    public DeptVO getOneDept(Integer deptno) {
        return dao.findByPrimaryKey(deptno);
    }

    @Override
    public List<EmpVO> getEmpsByDeptno(Integer deptno) {
        return dao.findByPrimaryKey(deptno).getEmpVOs();
    }

    @Override
    public void deleteDept(Integer deptno) {
        dao.delete(deptno);
    }

}
