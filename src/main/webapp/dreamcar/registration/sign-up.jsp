<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:url var="signUp" value=""/>
<form:form action="${signUp}" method="post" modelAttribute="User">
    <form:label path="firstName">Cantity: </form:label> <form:input type="text" path="firstName"/>
    <form:label path="lastName">Category: </form:label> <form:input type="text" path="lastName"/>
    <form:label path="email">Target Price: </form:label> <form:input type="email" path="email"/>
    <form:label path="password">Category: </form:label> <form:input type="password" path="password"/>
    <form:label path="password">Target Price: </form:label> <form:input type="password" path="password2"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>