package org.tutorial.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptDO implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
}
