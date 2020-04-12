<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>全部員工資料</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
    <c:if test="${not empty errorMsgs}">
        <font color='red'>請修正以下錯誤:
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </font>
    </c:if>
    <jsp:useBean id="deptSvc" scope="page" class="org.tutorial.service.impl.DeptServiceImpl" />
    <jsp:useBean id="empSvc" scope="page" class="org.tutorial.service.impl.EmpServiceImpl" />
    <table>
        <tr>
            <th>員工編號</th>
            <th>員工姓名</th>
            <th>職位</th>
            <th>雇用日期</th>
            <th>薪水</th>
            <th>獎金</th>
            <th>部門</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        <c:forEach var="empVO" items="${empSvc.all}">
            <tr align='center' valign='middle'>
                <td>${empVO.empno}</td>
                <td>${empVO.ename}</td>
                <td>${empVO.job}</td>
                <td>${empVO.hiredate}</td>
                <td>${empVO.sal}</td>
                <td>${empVO.comm}</td>
                <td>${empVO.deptno}
                    <c:forEach var="deptVO" items="${deptSvc.all}">
                        <c:if test="${empVO.deptno==deptVO.deptno}">
                            【${deptVO.dname} - ${deptVO.loc}】
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/emp/emp.do">
                        <input type="submit" value="修改">
                        <input type="hidden" name="empno" value="${empVO.empno}">
                        <input type="hidden" name="action"	value="getOne_For_Update">
                    </form>
                </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/emp/emp.do">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="empno" value="${empVO.empno}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>
</body>
</html>
