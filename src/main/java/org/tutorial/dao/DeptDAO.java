package org.tutorial.dao;

import java.util.List;

import org.tutorial.model.DeptVO;

public interface DeptDAO {

    void insert(DeptVO deptVO);

    void update(DeptVO deptVO);

    void delete(Integer deptno);

    DeptVO findByPrimaryKey(Integer deptno);

    List<DeptVO> getAll();

    List<DeptVO> findByCriteria(DeptVO deptVO);

}
