<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>部門資料修改</title>
</head>
<body>
    <h3>部門資料修改</h3>
    <form method="POST" action="${pageContext.request.contextPath}/dept/update" name="form1">
        <table>
            <tr>
                <td>部門編號:<font color=red><b>*</b></font></td>
                <td>${deptVO.deptno}</td>
            </tr>
            <tr>
                <td>部門名稱:</td>
                <td>
                    <input type="TEXT" name="dname" size="45" value="${deptVO.dname}" />
                </td>
            </tr>
            <tr>
                <td>部門基地:</td>
                <td>
                    <input type="TEXT" name="loc" size="45"	value="${deptVO.loc}" />
                </td>
            </tr>
        </table>
        <br />
        <input type="hidden" name="deptno" value="${deptVO.deptno}">
        <a href="${pageContext.request.contextPath}/index">回首頁</a>
        <input type="submit" value="送出修改">
    </form>
</body>
</html>
