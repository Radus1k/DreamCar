<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="css/sign_up.css"%></style>
<html>
<head>
    <title>Adauga o licitatie</title>
</head>
<body>

<jsp:include page="navbar.jsp" />


<h2 style="padding: 20px; font-size:20px;">
    Adauga o licitatie
</h2>


<c:if test="${savedLic}">
    <div>Successfully added licitation!</div>
</c:if>

<c:url var="addLicitation" value=""/>
<form:form action="${addLicitation}" method="post" modelAttribute="licitation">
    <form:label path="cantity" cssStyle="line-height: 3;">Cantity: </form:label > <form:input type="number" path="cantity" class="formInputs" cssStyle="line-height: 3;"/>
    <form:label path="category">Category: </form:label> <form:input type="text" path="category" class="formInputs" />
    <form:label path="targetPrice">Target Price: </form:label> <form:input path="targetPrice"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>