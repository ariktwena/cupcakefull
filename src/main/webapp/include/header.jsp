<%@ page import="modelLayer.Customer" %><%-- Header: User: jobe Date: 2019-03-05 Time: 16:45 --%>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Olsker Cupcake Webshop</title>
    <link href="css/cupcake.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Optional JavaScript: jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>

<body>

    <%
       // Check om brugeren er logget på
       Customer customer = (Customer)session.getAttribute("customer");
       String username = "<a href=\"FrontController?source=nav&action=login\">log på</a>";
       String logout = " (<a href=\"FrontController?source=logout\">log ud</a>)";
       int userid = 0;
       double balance = 0.0;

       Boolean isAdmin = false;
        if (customer != null){
            username = customer.getEmail() + logout;
            isAdmin = customer.getUsertype().equals("admin");
            balance = customer.getBalance();
            userid = customer.getCustomerId();
        }
    %>

    <div class="container">

        <img src="img/olskercupcakes.png" alt="Logo" class="img-fluid"/>

        <nav class="navbar navbar-expand-lg navbar-light " style="background-color:#e9ecef;">
            <a class="navbar-brand" href="#">
                <img src="img/cupcake.svg" width="30" height="30" class="d-inline-block align-top" alt="logo">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Shop</a>
                    </li>
                    <% if (isAdmin){ %>
                        <li class="nav-item">
                            <a class="nav-link" href="FrontController?source=nav&action=customers">Kunder</a>
                        </li>
                    <%}%>
                    <% if (userid > 0){ %>
                        <li class="nav-item">
                            <a class="nav-link" href="FrontController?source=orders">Ordrer</a>
                        </li>
                    <%}%>

                </ul>
                <ul class="navbar-nav">
                    <li>
                        <span style="margin-right:10px;"><%=username%></span>
                    </li>
                    <li>
                        <span style="margin-right:20px;">(saldo: <%=balance%> DKK)</span>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li>
                        <a class="nav-link" href="FrontController?source=nav&action=cart">
                            <img src="img/shopping-basket.svg" width="30" height="30" class="d-inline-block align-top"
                                 alt="logo">
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

