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
    <h3>Showcase id ${pageContext.request.getParameter("showcaseId")}</h3>
    <table>
        <thead>
        <th>ID</th>
        <th>Item</th>
        <th>Count</th>
        </thead>
        <c:forEach items="${allShowcasesItems}" var="showcasesItem">
            <tr>
                <td>${showcasesItem.id}</td>
                <td>${showcasesItem.item}</td>
                <td>${showcasesItem.quantity}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/warehouse/delete" method="post">
                        <input type="hidden" name="itemId" value="${showcasesItem.id}"/>
                        <input type="hidden" name="showcaseId" value="${pageContext.request.getParameter("showcaseId")}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/warehouse/addItem" method="get">
        <input type="hidden" name="showcaseId" value="${pageContext.request.getParameter("showcaseId")}"/>
        <input type="hidden" name="action" value="add"/>
        <button type="submit">Add</button>
    </form>
    <a href="/">Главная</a>
</div>
</body>
</html>