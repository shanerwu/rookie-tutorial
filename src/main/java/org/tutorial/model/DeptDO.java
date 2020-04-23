package org.tutorial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "DEPT2")
@Entity
@NamedQuery(name = "dept.all", query = "SELECT d FROM DeptDO d")
public class DeptDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSeq")
    @SequenceGenerator(name = "deptSeq", sequenceName = "dept2_seq", allocationSize = 1)
    @Column(name = "DEPTNO", nullable = false, columnDefinition = "NUMBER(3)")
    private Integer deptno;

    @Column(name = "DNAME", columnDefinition = "VARCHAR2(14 CHAR)")
    private String dname;

    @Column(name = "LOC", columnDefinition = "VARCHAR2(13 CHAR)")
    private String loc;

    @OneToMany(mappedBy = "deptDO", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<EmpDO> empDOS;
}
