<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@ page import="facadeLayer.OrderFacade" %>
<%@ page import="modelLayer.*" %>
<%@ page import="java.sql.Date" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="include/header.jsp" %>

<div class="jumbotron" style="background-color: transparent;">
    <h1>Ordrer</h1>

    <form action="/FrontController" method="post">

        <input type="hidden" name="source" value="orders"/>

        <table class="table table-striped ">
            <thead>
            <tr>
                <th>Ordre Nr</th>
                <th>Kunde Nr</th>
                <th>Email</th>
                <th>Ordredato</th>
                <th>Ordrelinier</th>
            </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Order> orderList = null;
                    String sCustomerId = request.getParameter("customerid");
                    if (userid > 0) {
                        orderList = OrderFacade.getOrderListByCustomerId(userid);
                    } else
                    if (sCustomerId != null){
                        orderList = OrderFacade.getOrderListByCustomerId(Integer.parseInt(sCustomerId));
                    } else {
                        orderList = OrderFacade.getOrderList();
                    }
                    if (orderList != null) {
                        for (Order o: orderList) {
                            int order_id = o.getOrder_id();
                            int customer_id = o.getCustomer_id();
                            String customer_email = o.getEmail();
                            Date order_date = o.getOrdre_date();
                %>
                            <tr>
                                <td><%=order_id%></td>
                                <td><%=customer_id%></td>
                                <td><%=customer_email%></td>
                                <td><%=order_date%></td>
                                <td>
                                    <button type="submit" name="order_id" value="<%=order_id%>" class="btn btn-success btn-xs">Se ordreliner</button>
                                </td>
                            </tr>
                    <%
                        }
                    } else {
                        out.println("<p>Ingen ordrer</p>");
                    }
                %>
            </tbody>
        </table>
    </form>

    <form action="/FrontController" method="post">
        <div class="float-right">
            <input type="hidden" name="source" value="cartcontrol"/>
            <button type="submit" name="action" value="shop" class="btn btn-primary">Shop videre</button>
        </div>
    </form>

</div>

<%@ include file="include/footer.jsp" %>

