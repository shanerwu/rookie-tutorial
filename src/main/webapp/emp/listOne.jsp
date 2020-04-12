<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>員工資料</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
    <h3>員工資料</h3>
    <table>
        <tr>
            <th>員工編號</th>
            <th>員工姓名</th>
            <th>職位</th>
            <th>雇用日期</th>
            <th>薪水</th>
            <th>獎金</th>
            <th>部門</th>
        </tr>
        <tr align='center' valign='middle'>
            <td>${empVO.empno}</td>
            <td>${empVO.ename}</td>
            <td>${empVO.job}</td>
            <td>${empVO.hiredate}</td>
            <td>${empVO.sal}</td>
            <td>${empVO.comm}</td>
            <td>${empVO.deptno}
                <jsp:useBean id="deptSvc" scope="page" class="org.tutorial.service.impl.DeptServiceImpl" />
                <c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
                        【${deptVO.dname} - ${deptVO.loc}】
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>
</body>
</html>
