<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Roles</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h3>${pageContext.request.getParameter("userId")}</h3>
    <table>
        <thead>
        <th>ID</th>
        <th>Name</th>
        </thead>
        <c:forEach items="${allRoles}" var="role">
            <tr>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/setRole" method="post">
                        <input type="hidden" name="roleId" value="${role.id}"/>
                        <input type="hidden" name="userId" value=${pageContext.request.getParameter("userId")}>
                        <input type="hidden" name="action" value="add"/>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>