<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>All Deals</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:set var="count" value="0" scope="page" />

<h2 style="padding: 20px; font-size:20px; ">
    Toate ofertele licitatiilor active.
</h2>

<table>
    <thead>
    <tr>
        <th>ID Deal</th>
        <th>Pret</th>
        <th>Id Licitatie</th>

    </tr>
    </thead>
</table>
</body>
</html>

<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col"> Index </th>
        <th scope="col" >ID Deal</th>
        <th scope="col">Pret</th>
        <th scope="col">Id Licitatie</th>
    </tr>
    </thead>
        <c:forEach items="${dealList}" var="deal">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tbody>
        <tr>
            <td>${count}</td>
            <td>${deal.getidDeals()}</td>
            <td>${deal.price}</td>
            <td>${deal.idLicitation}</td>
        </tr>
    </c:forEach>
    </tbody>