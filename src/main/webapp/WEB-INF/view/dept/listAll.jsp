<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>所有部門</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
    <h3>所有部門</h3>
    <table>
        <tr>
            <th>部門編號</th>
            <th>部門名稱</th>
            <th>部門基地</th>
            <th>修改</th>
            <th>刪除<font color=red>(關聯測試)</font></th>
            <th>查詢部門員工</th>
        </tr>

        <c:forEach var="deptVO" items="${deptVOS}">
            <tr align='center' valign='middle'>
                <td>${deptVO.deptno}</td>
                <td>${deptVO.dname}</td>
                <td>${deptVO.loc}</td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/dept/getOne_For_Update_Dept">
                        <input type="submit" value="修改">
                        <input type="hidden" name="deptno" value="${deptVO.deptno}">
                    </form>
                </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/dept/delete_Dept">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="deptno" value="${deptVO.deptno}">
                    </form>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/dept/listEmps_ByDeptno_B/${deptVO.deptno}">送出查詢</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br />
    <c:choose>
        <c:when test="${listEmps_ByDeptno != null}">
            <jsp:include page="listEmpsByDeptno.jsp"/>
        </c:when>
        <c:otherwise>
            <br />
            <a href="${pageContext.request.contextPath}/index">回首頁</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
