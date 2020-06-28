package org.tutorial.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpDO implements Serializable {
    private Integer empno;
    private String ename;
    private String job;
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
}
