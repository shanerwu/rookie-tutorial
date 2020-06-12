package org.tutorial.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tutorial.dao.EmpDAO;
import org.tutorial.model.EmpDO;

public class EmpDAOImpl implements EmpDAO {
    String driver = "oracle.jdbc.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:32769:ORCLCDB";
    String userid = "sa";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO emp2 (ename,job,hiredate,sal,comm,deptno) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 order by empno";
    private static final String GET_ONE_STMT = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where empno = ?";
    private static final String DELETE = "DELETE FROM emp2 where empno = ?";
    private static final String UPDATE = "UPDATE emp2 set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

    @Override
    public void insert(EmpDO empDO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, empDO.getEname());
            pstmt.setString(2, empDO.getJob());
            pstmt.setDate(3, Date.valueOf(empDO.getHiredate()));
            pstmt.setDouble(4, empDO.getSal());
            pstmt.setDouble(5, empDO.getComm());
            pstmt.setInt(6, empDO.getDeptno());

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
    public void update(EmpDO empDO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, empDO.getEname());
            pstmt.setString(2, empDO.getJob());
            pstmt.setDate(3, Date.valueOf(empDO.getHiredate()));
            pstmt.setDouble(4, empDO.getSal());
            pstmt.setDouble(5, empDO.getComm());
            pstmt.setInt(6, empDO.getDeptno());
            pstmt.setInt(7, empDO.getEmpno());

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
    public void delete(Integer empno) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, empno);

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
    public EmpDO findByPrimaryKey(Integer empno) {

        EmpDO empDO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, empno);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empDO 也稱為 Domain objects
                empDO = new EmpDO();
                empDO.setEmpno(rs.getInt("empno"));
                empDO.setEname(rs.getString("ename"));
                empDO.setJob(rs.getString("job"));
                empDO.setHiredate(rs.getDate("hiredate").toLocalDate());
                empDO.setSal(rs.getDouble("sal"));
                empDO.setComm(rs.getDouble("comm"));
                empDO.setDeptno(rs.getInt("deptno"));
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
        return empDO;
    }

    @Override
    public List<EmpDO> getAll() {
        List<EmpDO> list = new ArrayList<>();
        EmpDO empDO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empDO 也稱為 Domain objects
                empDO = new EmpDO();
                empDO.setEmpno(rs.getInt("empno"));
                empDO.setEname(rs.getString("ename"));
                empDO.setJob(rs.getString("job"));
                empDO.setHiredate(rs.getDate("hiredate").toLocalDate());
                empDO.setSal(rs.getDouble("sal"));
                empDO.setComm(rs.getDouble("comm"));
                empDO.setDeptno(rs.getInt("deptno"));
                list.add(empDO); // Store the row in the list
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
        return list;
    }

}
