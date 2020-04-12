<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>部門資料修改</title>
</head>
<body>
    <h3>部門資料修改</h3>
    <c:if test="${not empty errorMsgs}">
        <font color='red'>請修正以下錯誤:
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </font>
    </c:if>
    <form method="POST" action="dept.do" name="form1">
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
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="deptno" value="${deptVO.deptno}">
        <a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>
        <input type="submit" value="送出修改">
    </form>
</body>
</html>
