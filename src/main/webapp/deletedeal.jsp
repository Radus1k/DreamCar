<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Deal</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2 style="padding: 20px; font-size:20px;">
    Sterge o licitatie
</h2>

<c:url var="deletedeal" value=""/>
<form:form action="#" method="delete" modelAttribute="deal">
    <form:label path="idDeals">ID Deal: </form:label> <form:input type="number" path="idDeals"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>