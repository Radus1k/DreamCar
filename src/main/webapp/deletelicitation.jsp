<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Licitation</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:url var="deletelicitation" value=""/>
<form:form action="#" method="delete" modelAttribute="licitation">
    <form:label path="id_licitation">ID Licitation: </form:label> <form:input type="number" path="id_licitation"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>