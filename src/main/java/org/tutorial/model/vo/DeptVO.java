package org.tutorial.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVO {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<EmpVO> empVOs;
}
