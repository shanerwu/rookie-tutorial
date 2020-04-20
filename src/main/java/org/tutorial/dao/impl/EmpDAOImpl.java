package org.tutorial.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpVO;
import org.tutorial.utils.JPAUtil;

public class EmpDAOImpl implements EmpDAO {

    @Override
    public void insert(EmpVO empVO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(empVO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(EmpVO empVO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(empVO);
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
            EmpVO empVO = entityManager.find(EmpVO.class, empno);
            entityManager.remove(empVO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public EmpVO findByPrimaryKey(Integer empno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(EmpVO.class, empno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmpVO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        Query query = entityManager.createNamedQuery("emp.all");
        //JPQL Query
//        Query query = entityManager.createQuery("select emp from EmpVO emp");
        //Native Query
//        Query query = entityManager.createNativeQuery("select * from emp2", EmpVO.class);
        return query.getResultList();
    }
}
