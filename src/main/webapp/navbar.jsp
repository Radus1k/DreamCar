<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"  href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
      integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
      crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
        background-color: white;
    }

    * {
        box-sizing: border-box;
    }

    /* Add padding to containers */
    .container {
        padding: 16px;
        background-color: white;
    }

    /* Full-width input fields */
    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Overwrite default styles of hr */
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* Set a style for the submit button */
    .registerbtn {
        background-color: #04AA6D;
        color: white;
        padding: 16px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    .registerbtn:hover {
        opacity: 1;
    }

    /* Add a blue text color to links */
    a {
        color: dodgerblue;
    }

    /* Set a grey background color and center the text of the "sign in" section */
    .signin {
        background-color: #f1f1f1;
        text-align: center;
    }
</style>


<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">DREAMCAR</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="desprePage.jsp">Despre</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/add_licitation">Adauga Licitatie</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add_deal">Adauga Oferta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="all_deals">Vezi toate Ofertele</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="all_licitations">Vezi toate licitatiile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="update_deal">Update Oferta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="delete_deal">Stergere Oferta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/delete_licitation">Stergere Licitatie</a>
                </li>
            </ul>
            <ul class="navbar-nav pull-right">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>

<%--            <form class="d-flex">--%>
<%--                <input class="form-control me-2" type="text" placeholder="Search">--%>
<%--                <button class="btn btn-primary" type="button">Search</button>--%>
<%--            </form>--%>
        </div>
    </div>
</nav>