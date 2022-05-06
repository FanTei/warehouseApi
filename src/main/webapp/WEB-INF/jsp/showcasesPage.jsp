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
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <table>
        <thead>
        <th>ID</th>
        <th>Title</th>
        <th>Size</th>
        <th>Count</th>
        </thead>
        <c:forEach items="${allShowcases}" var="showcase">
            <tr>
                <td>${showcase.id}</td>
                <td>${showcase.title}</td>
                <td>${showcase.size}</td>
                <td>Count</td>
                <td>
                    <form action="${pageContext.request.contextPath}/showcases/update" method="get">
                        <input type="hidden" name="showcaseId" value=${showcase.id}>
                        <input type="hidden" name="action" value="items"/>
                        <button type="submit">Edit</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/warehouse/getItems" method="get">
                        <input type="hidden" name="showcaseId" value=${showcase.id}>
                        <input type="hidden" name="action" value="items"/>
                        <button type="submit">Items</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/showcases/delete" method="post">
                        <input type="hidden" name="showcaseId" value="${showcase.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>

                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/showcases/create" method="get">
        <input type="hidden" name="action" value="items"/>
        <button type="submit">Add</button>
    </form>
    <a href="/">Главная</a>
</div>
</body>
</html>