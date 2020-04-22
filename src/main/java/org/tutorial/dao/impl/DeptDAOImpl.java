package org.tutorial.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptVO;

@Repository
public class DeptDAOImpl implements DeptDAO {

    private static final String WILD_CARD = "%";

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void insert(DeptVO deptVO) {
        entityManager.persist(deptVO);
    }

    @Override
    @Transactional
    public void update(DeptVO deptVO) {
        DeptVO deptVOFromDB = entityManager.find(DeptVO.class, deptVO.getDeptno());
        if (deptVOFromDB != null) {
            deptVOFromDB.setDeptno(deptVO.getDeptno());
            deptVOFromDB.setDname(deptVO.getDname());
            deptVOFromDB.setLoc(deptVO.getLoc());
        }
    }

    @Override
    @Transactional
    public void delete(Integer deptno) {
        DeptVO deptVO = entityManager.find(DeptVO.class, deptno);
        entityManager.remove(deptVO);
    }

    @Override
    public DeptVO findByPrimaryKey(Integer deptno) {
        return entityManager.find(DeptVO.class, deptno);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeptVO> getAll() {
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
