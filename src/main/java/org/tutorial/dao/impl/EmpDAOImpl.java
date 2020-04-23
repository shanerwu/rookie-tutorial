package org.tutorial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;

@Repository
public class EmpDAOImpl implements EmpDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void insert(EmpDO empDO) {
        entityManager.persist(empDO);
    }

    @Override
    @Transactional
    public void update(EmpDO empDO) {
        entityManager.merge(empDO);
    }

    @Override
    @Transactional
    public void delete(Integer empno) {
        EmpDO empDO = entityManager.find(EmpDO.class, empno);
        entityManager.remove(empDO);
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
        return entityManager.find(EmpDO.class, empno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmpDO> getAll() {
        //Name Query
        Query query = entityManager.createNamedQuery("emp.all");
        //JPQL Query
//        Query query = entityManager.createQuery("select emp from EmpVO emp");
        //Native Query
//        Query query = entityManager.createNativeQuery("select * from emp2", EmpVO.class);
        return query.getResultList();
    }
}
