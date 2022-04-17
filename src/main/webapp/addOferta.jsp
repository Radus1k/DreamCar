<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="css/sign_up.css"%></style>
<html>
<head>
    <title>Add Offer</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2 style="padding: 20px; font-size:20px">
    Adauga o oferta
</h2>

<c:url var="addOffer" value=""/>
<form:form action="${addOffer}" method="post" modelAttribute="deal">
    <form:label path="price" cssClass="formLabels">Price: </form:label> <form:input type="number" path="price"  class="formInputs" cssStyle="line-height: 3;"/>
    <form:label path="idLicitation" cssClass="formLabels">ID Licitatie: </form:label> <form:input type="number" path="idLicitation" cssStyle="line-height: 3;" class="formInputs"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>