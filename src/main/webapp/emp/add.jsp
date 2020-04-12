<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>員工資料新增</title>
</head>
<body>
    <h3>員工資料</h3>
    <c:if test="${not empty errorMsgs}">
        <font color='red'>請修正以下錯誤:
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </font>
    </c:if>
    <form method="POST" action="emp.do" name="form1">
        <table>
            <tr>
                <td>員工姓名:</td>
                <td>
                    <input type="TEXT" name="ename" size="45" value="${(empVO==null)? "王小明" : empVO.getEname()}" />
                </td>
            </tr>
            <tr>
                <td>職位:</td>
                <td>
                    <input type="TEXT" name="job" size="45" value="${(empVO==null)? "manager" : empVO.getJob()}" />
                </td>
            </tr>
            <tr>
                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />
                <td>雇用日期:</td>
                <td>
                    <input type="date" name="hiredate" value="${(empVO==null)? currentDate : empVO.getHiredate()}" />
                </td>
            </tr>
            <tr>
                <td>薪水:</td>
                <td>
                    <input type="TEXT" name="sal" size="45" value="${(empVO==null)? "10000" : empVO.getSal()}" />
                </td>
            </tr>
            <tr>
                <td>獎金:</td>
                <td>
                    <input type="TEXT" name="comm" size="45" value="${(empVO==null)? "100" : empVO.getComm()}" />
                </td>
            </tr>

            <jsp:useBean id="deptSvc" scope="page" class="org.tutorial.service.impl.DeptServiceImpl" />
            <tr>
                <td>部門:<font color=red><b>*</b></font></td>
                <td>
                    <select size="1" name="deptno">
                        <c:forEach var="deptVO" items="${deptSvc.all}">
                            <option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <br />
        <input type="hidden" name="action" value="insert">
        <a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>
        <input type="submit" value="送出新增">
    </form>
</body>
</html>
