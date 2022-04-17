<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="css/sign_up.css"%></style>


<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:url var="signUp" value=""/>
<form:form action="${signUp}" method="post" modelAttribute="User">
    <form:label path="firstName">First Name: </form:label> <form:input type="text" path="firstName"/>
    <form:label path="lastName">Last Name: </form:label>
    <form:input type="text" path="lastName"/>
    <form:label path="email" cssClass="formLabels" >Email: </form:label> <form:input type="email" path="email" class="formInputs" cssStyle="line-height: 3;" />
    <form:label path="password" cssClass="formLabels" >Password: </form:label> <form:input type="password" path="password" class="formInputs"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>