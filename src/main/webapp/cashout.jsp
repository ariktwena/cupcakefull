<%@ page import="modelLayer.Bottom" %>
<%@ page import="modelLayer.Top" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file = "include/header.jsp" %>

    <div class="jumbotron">
        <h1>Kvittering</h1>

        <%  String besked = (String) request.getAttribute("message");
            String status = (String) request.getAttribute("status");
            String alertType;

            if (status != null && status.equals("OK")) {
                alertType = "alert-success";
                besked = besked + "<p>Tak for din bestilling. Du kan afhente dine cupcakes i Olsker allerede om 10 minutter.</p>";
            } else {
                alertType = "alert-danger";
            }
            if (besked != null ) {
                String alert = "<div class=\"alert " + alertType +  "\">_message_</div>";
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



<%@ include file = "include/footer.jsp" %>

