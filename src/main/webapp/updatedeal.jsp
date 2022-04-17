<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Deal</title>
</head>
<body>
<jsp:include page="navbar.jsp" />


<h2 style="padding: 20px; font-size:20px">
    Actualizeaza o oferta
</h2>

<c:url var="updatedeal" value=""/>
<form:form action="#" method="post" modelAttribute="deal">
    <form:label path="idDeals">Id Oferta:</form:label> <form:input type="text" path="idDeals"/>
    <form:label path="price">Pret: </form:label> <form:input type="text" path="price"/>
    <form:label path="idLicitation">Id Licitatie </form:label> <form:input path="idLicitation"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>