package org.tutorial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpVO;

@Repository
public class EmpDAOImpl implements EmpDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void insert(EmpVO empVO) {
        entityManager.persist(empVO);
    }

    @Override
    @Transactional
    public void update(EmpVO empVO) {
        entityManager.merge(empVO);
    }

    @Override
    @Transactional
    public void delete(Integer empno) {
        EmpVO empVO = entityManager.find(EmpVO.class, empno);
        entityManager.remove(empVO);
    }

    @Override
    public EmpVO findByPrimaryKey(Integer empno) {
        return entityManager.find(EmpVO.class, empno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmpVO> getAll() {
        //Name Query
        Query query = entityManager.createNamedQuery("emp.all");
        //JPQL Query
//        Query query = entityManager.createQuery("select emp from EmpVO emp");
        //Native Query
//        Query query = entityManager.createNativeQuery("select * from emp2", EmpVO.class);
        return query.getResultList();
    }
}
