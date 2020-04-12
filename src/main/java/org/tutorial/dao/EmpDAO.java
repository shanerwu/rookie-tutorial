package org.tutorial.dao;

import java.util.List;

import org.tutorial.model.EmpVO;

public interface EmpDAO {

    void insert(EmpVO empVO);

    void update(EmpVO empVO);

    void delete(Integer empno);

    EmpVO findByPrimaryKey(Integer empno);

    List<EmpVO> getAll();

}
