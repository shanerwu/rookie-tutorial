package org.tutorial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptDO;
import org.tutorial.model.EmpDO;
import org.tutorial.utils.JPAUtil;

public class DeptDAOImpl implements DeptDAO {

    private static final String WILD_CARD = "%";

    @Override
    public void insert(DeptDO deptDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(deptDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(DeptDO deptDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        DeptDO deptDOFromDB = entityManager.find(DeptDO.class, deptDO.getDeptno());
        if (deptDOFromDB != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                deptDOFromDB.setDeptno(deptDO.getDeptno());
                deptDOFromDB.setDname(deptDO.getDname());
                deptDOFromDB.setLoc(deptDO.getLoc());
                transaction.commit();
                entityManager.close();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DeptDO deptDO = entityManager.find(DeptDO.class, deptno);
            entityManager.remove(deptDO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public DeptDO findByPrimaryKey(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(DeptDO.class, deptno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeptDO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        Query query = entityManager.createNamedQuery("dept.all");
        //JPQL Query
//        Query query = entityManager.createQuery("SELECT dept FROM DeptDO dept");
        //Native Query
//        Query query = entityManager.createNativeQuery("SELECT * FROM dept2", DeptDO.class);
        return query.getResultList();
    }

    @Override
    public List<EmpDO> getEmpsByDeptno(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<EmpDO> query = entityManager.createQuery("SELECT emp FROM EmpDO emp WHERE emp.deptno = :deptno", EmpDO.class);
        query.setParameter("deptno", deptno);
        return query.getResultList();
    }

    @Override
    public List<DeptDO> findByCriteria(DeptDO deptDO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptDO> criteriaQuery = criteriaBuilder.createQuery(DeptDO.class);
        Root<DeptDO> column = criteriaQuery.from(DeptDO.class);

        List<Predicate> predicates = new ArrayList<>();
        if (deptDO.getDeptno() != null) {
            predicates.add(criteriaBuilder.equal(column.get("deptno"), deptDO.getDeptno()));
        }

        if (StringUtils.isNoneBlank(deptDO.getDname())) {
            predicates.add(criteriaBuilder.like(column.get("dname"), WILD_CARD + deptDO.getDname() + WILD_CARD));
        }

        if (StringUtils.isNoneBlank(deptDO.getLoc())) {
            predicates.add(criteriaBuilder.like(column.get("loc"), WILD_CARD + deptDO.getLoc() + WILD_CARD));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<DeptDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
