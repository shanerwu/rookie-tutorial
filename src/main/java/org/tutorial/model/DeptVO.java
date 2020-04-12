package org.tutorial.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeptVO implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
}
