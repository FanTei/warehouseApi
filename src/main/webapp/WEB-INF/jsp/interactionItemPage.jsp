<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add item</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="itemForm">

        <h2>Add item</h2>
        <div>
            <form:input type="text" path="title" placeholder="Title"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="occupiedSize" placeholder="OccupiedSize"></form:input>
        </div>
        <div>
            <form:input type="text" path="price" placeholder="Price"></form:input>
        </div>
        <button type="submit">Add</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>