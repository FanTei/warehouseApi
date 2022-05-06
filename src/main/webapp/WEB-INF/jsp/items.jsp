<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Предметы</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
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

                    <form action="${pageContext.request.contextPath}/items/delete" method="post">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/items/update" method="get">
                        <input type="hidden" name="itemId" value="${item.id}"/>
                        <input type="hidden" name="action" value="edit"/>
                        <button type="submit">Edit</button>
                    </form>

                </td>
            </tr>
        </c:forEach>
    </table>
    <sec:authorize access="hasRole('ADMIN')">
    <h4><a href="/items/create">Добавить</a></h4>
    </sec:authorize>
    <a href="/">Главная</a>
</div>
</body>
</html>