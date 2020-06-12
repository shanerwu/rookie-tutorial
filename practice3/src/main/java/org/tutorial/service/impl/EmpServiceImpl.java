package org.tutorial.service.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;
import org.tutorial.service.EmpService;

public class EmpServiceImpl implements EmpService {

    private EmpDAO dao;

    public EmpServiceImpl() {
        // 將 Dao 物件生成交由 Spring 管理
//        dao = new EmpDAOImpl();

        // 此種取得 Spring Bean 的方式為暫時測試用
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("empDAOImpl", EmpDAO.class);
    }

    @Override
    public EmpDO addEmp(EmpDO empDO) {
        dao.insert(empDO);
        return empDO;
    }

    @Override
    public EmpDO updateEmp(EmpDO empDO) {
        dao.update(empDO);
        return dao.findByPrimaryKey(empDO.getEmpno());
    }

    @Override
    public void deleteEmp(Integer empno) {
        dao.delete(empno);
    }

    @Override
    public EmpDO getOneEmp(Integer empno) {
        return dao.findByPrimaryKey(empno);
    }

    @Override
    public List<EmpDO> getAll() {
        return dao.getAll();
    }

}
