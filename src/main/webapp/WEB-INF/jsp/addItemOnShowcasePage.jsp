<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Предметы</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h3>${pageContext.request.getParameter("showcaseId")}</h3>
    <table>
        <thead>
        <th>ID</th>
        <th>Title</th>
        <th>Size</th>
        <th>Price</th>
        </thead>
        <c:forEach items="${allItems}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.title}</td>
                <td>${item.occupiedSize}</td>
                <td>${item.price}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/warehouse/add" method="post">
                        <input type="hidden" name="itemId" value="${item.id}"/>
                        <input type="hidden" name="showcaseId" value=${pageContext.request.getParameter("showcaseId")}>
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