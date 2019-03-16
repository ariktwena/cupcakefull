<%@ page import="modelLayer.Bottom" %>
<%@ page import="modelLayer.Top" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file = "../include/header.jsp" %>

    <div class="jumbotron">
        <h1>Der er opstået en fejl</h1>

        <%  String besked = (String)request.getAttribute("message");

            if (besked != null ) {
                String alert = "<div class=\"alert " + "alert-danger" +  "\">_message_</div>";
                alert = alert.replace("_message_", besked);
                out.println(alert);
            }

        %>

        <form action="FrontController" method="post">
            <div class="float-right">
                <input type="hidden" name="source" value="cartcontrol"/>
                <button type="submit" name="action" value="shop" class="btn btn-primary">Shop videre</button>
            </div>
        </form>

    </div>



<%@ include file = "../include/footer.jsp" %>

