package org.tutorial.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.tutorial.dao.DeptDAO;
import org.tutorial.model.DeptVO;
import org.tutorial.model.EmpVO;

public class DeptDAOImpl implements DeptDAO {
    String driver = "oracle.jdbc.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:32769:ORCLCDB";
    String userid = "sa";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO dept2 (dname,loc) VALUES (?, ?)";
    private static final String GET_ALL_STMT = "SELECT deptno , dname, loc FROM dept2";
    private static final String GET_ONE_STMT = "SELECT deptno , dname, loc FROM dept2 where deptno = ?";
    private static final String GET_Emps_ByDeptno_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";

    private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
    private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";

    private static final String UPDATE = "UPDATE dept2 set dname=?, loc=? where deptno = ?";

    @Override
    public void insert(DeptVO deptVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, deptVO.getDname());
            pstmt.setString(2, deptVO.getLoc());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void update(DeptVO deptVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, deptVO.getDname());
            pstmt.setString(2, deptVO.getLoc());
            pstmt.setInt(3, deptVO.getDeptno());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void delete(Integer deptno) {
        int updateCount_EMPs = 0;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            // 1.設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            // 先刪除員工
            pstmt = con.prepareStatement(DELETE_EMPs);
            pstmt.setInt(1, deptno);
            updateCount_EMPs = pstmt.executeUpdate();
            // 再刪除部門
            pstmt = con.prepareStatement(DELETE_DEPT);
            pstmt.setInt(1, deptno);
            pstmt.executeUpdate();

            // 2.設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("刪除部門編號" + deptno + "時,共有員工" + updateCount_EMPs
                    + "人同時被刪除");

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3.設定於當有exception發生時之catch區塊內
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. "
                            + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public DeptVO findByPrimaryKey(Integer deptno) {

        DeptVO deptVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, deptno);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // deptVO 也稱為 Domain objects
                deptVO = new DeptVO();
                deptVO.setDeptno(rs.getInt("deptno"));
                deptVO.setDname(rs.getString("dname"));
                deptVO.setLoc(rs.getString("loc"));
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return deptVO;
    }

    @Override
    public List<DeptVO> getAll() {
        List<DeptVO> list = new ArrayList<>();
        DeptVO deptVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                deptVO = new DeptVO();
                deptVO.setDeptno(rs.getInt("deptno"));
                deptVO.setDname(rs.getString("dname"));
                deptVO.setLoc(rs.getString("loc"));
                list.add(deptVO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

    @Override
    public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
        Set<EmpVO> set = new LinkedHashSet<>();
        EmpVO empVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);
            pstmt.setInt(1, deptno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpno(rs.getInt("empno"));
                empVO.setEname(rs.getString("ename"));
                empVO.setJob(rs.getString("job"));
                empVO.setHiredate(rs.getDate("hiredate"));
                empVO.setSal(rs.getDouble("sal"));
                empVO.setComm(rs.getDouble("comm"));
                empVO.setDeptno(rs.getInt("deptno"));
                set.add(empVO); // Store the row in the vector
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return set;
    }

}
