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
import org.tutorial.model.DeptVO;
import org.tutorial.utils.JPAUtil;

public class DeptDAOImpl implements DeptDAO {

    private static final String WILD_CARD = "%";

    @Override
    public void insert(DeptVO deptVO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(deptVO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(DeptVO deptVO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        DeptVO deptVOFromDB = entityManager.find(DeptVO.class, deptVO.getDeptno());
        if (deptVOFromDB != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                deptVOFromDB.setDeptno(deptVO.getDeptno());
                deptVOFromDB.setDname(deptVO.getDname());
                deptVOFromDB.setLoc(deptVO.getLoc());
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
            DeptVO deptVO = entityManager.find(DeptVO.class, deptno);
            entityManager.remove(deptVO);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public DeptVO findByPrimaryKey(Integer deptno) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        return entityManager.find(DeptVO.class, deptno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeptVO> getAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        //Name Query
        Query query = entityManager.createNamedQuery("dept.all");
        //JPQL Query
//        Query query = entityManager.createQuery("select dept from DeptVO dept");
        //Native Query
//        Query query = entityManager.createNativeQuery("select * from dept2", DeptVO.class);
        return query.getResultList();
    }

    @Override
    public List<DeptVO> findByCriteria(DeptVO deptVO) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptVO> criteriaQuery = criteriaBuilder.createQuery(DeptVO.class);
        Root<DeptVO> column = criteriaQuery.from(DeptVO.class);

        List<Predicate> predicates = new ArrayList<>();
        if (deptVO.getDeptno() != null) {
            predicates.add(criteriaBuilder.equal(column.get("deptno"), deptVO.getDeptno()));
        }

        if (StringUtils.isNoneBlank(deptVO.getDname())) {
            predicates.add(criteriaBuilder.like(column.get("dname"), WILD_CARD + deptVO.getDname() + WILD_CARD));
        }

        if (StringUtils.isNoneBlank(deptVO.getLoc())) {
            predicates.add(criteriaBuilder.like(column.get("loc"), WILD_CARD + deptVO.getLoc() + WILD_CARD));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<DeptVO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
