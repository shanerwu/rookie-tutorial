<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="deptSvc" scope="page" class="org.tutorial.service.impl.DeptServiceImpl"/>
<html>
<head>
    <title>┮Τ场</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
    <h3>┮Τ场</h3>
    <c:if test="${not empty errorMsgs}">
        <font color='red'>叫タ岿粇:
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </font>
    </c:if>

    <table>
        <tr>
            <th>场絪腹</th>
            <th>场嘿</th>
            <th>场膀</th>
            <th>э</th>
            <th>埃<font color=red>(闽羛代刚)</font></th>
            <th>琩高场</th>
        </tr>

        <c:forEach var="deptVO" items="${deptSvc.all}">
            <tr align='center' valign='middle'>
                <td>${deptVO.deptno}</td>
                <td>${deptVO.dname}</td>
                <td>${deptVO.loc}</td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/dept/dept.do">
                        <input type="submit" value="э">
                        <input type="hidden" name="deptno" value="${deptVO.deptno}">
                        <input type="hidden" name="action" value="getOne_For_Update_Dept">
                    </form>
                </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/dept/dept.do">
                        <input type="submit" value="埃">
                        <input type="hidden" name="deptno" value="${deptVO.deptno}">
                        <input type="hidden" name="action" value="delete_Dept">
                    </form>
                </td>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/dept/dept.do">
                        <input type="submit" value="癳琩高">
                        <input type="hidden" name="deptno" value="${deptVO.deptno}">
                        <input type="hidden" name="action" value="listEmps_ByDeptno_B">
                    </form>
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
            <a href="${pageContext.request.contextPath}/index.jsp"></a>
        </c:otherwise>
    </c:choose>
</body>
</html>
