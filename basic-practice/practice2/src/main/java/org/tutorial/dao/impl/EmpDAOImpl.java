package org.tutorial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;
import org.tutorial.utils.JPAUtil;

public class EmpDAOImpl implements EmpDAO {

    @Override
    public void insert(EmpDO empDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(empDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(EmpDO empDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(empDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer empno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EmpDO empDO = entityManager.find(EmpDO.class, empno);
            entityManager.remove(empDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public EmpDO findByPrimaryKey(Integer empno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(EmpDO.class, empno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmpDO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        Query query = entityManager.createNamedQuery("emp.all");
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT emp FROM EmpDO emp");
        //Native Query
//        Query query = entityManager.createNativeQuery("SELECT * FROM emp2", EmpDO.class);
        return query.getResultList();
    }

}
