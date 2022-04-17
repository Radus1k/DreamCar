<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View Licitations</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:set var="count" value="0" scope="page" />

<h2 style="padding: 20px; font-size:20px; background-color:#f1f1f1 !important; ">
    Toate licitatiile active/inactive
</h2>

<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">Index</th>
        <th scope="col">ID Licitatie</th>
        <th scope="col">Categorie</th>
        <th scope="col">Cantitate</th>
        <th scope="col">Pret</th>
        <th scope="col">Data Expirare Licitatie</th>
        <th scope="col">Status</th>
        <th scope="col">Oferte</th>
        <th scope="col">Winner</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listaLicitatii}" var="licitation">
        <c:set var="count" value="${count + 1}" scope="page"/>
    <tr>
        <th scope="row"> ${count} </th>
        <td>${licitation.getid_licitation()}</td>
        <td>${licitation.category}</td>
        <td>${licitation.cantity}</td>
        <td>${licitation.targetPrice}</td>
        <td>${licitation.deadline}</td>
        <td>${licitation.status}</td>
        <td>${licitation.viewDealList()}</td>
        <td>${licitation.winner}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>