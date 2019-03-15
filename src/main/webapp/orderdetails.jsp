<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@ page import="facadeLayer.OrderFacade" %>
<%@ page import="modelLayer.*" %>
<%@ page import="java.sql.Date" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="include/header.jsp" %>

<div class="jumbotron" style="background-color: transparent;">
    <h1>Ordredetaljer</h1>

    <form action="/FrontController" method="post">

        <input type="hidden" name="source" value="cart"/>

        <table class="table table-striped ">
            <thead>
            <tr>
                <th>Ordre Nr</th>
                <th>Linie Nr</th>
                <th>Email</th>
                <th>Antal</th>
                <th>Top</th>
                <th>Top pris</th>
                <th>Bund</th>
                <th>Bund pris</th>
                <th> I alt</th>
            </tr>
            </thead>
            <tbody>

                <%
                    int orderId = Integer.parseInt((String)request.getAttribute("order_id"));
                    ArrayList<OrderItems> orderItemList = OrderFacade.getOrderItemListByOrderId(orderId);
                    int grandTotalPrice = 0;
                    if (orderItemList != null) {
                        for (OrderItems oItem: orderItemList) {
                            int order_id = oItem.getOrder_id();
                            String email = oItem.getEmail();
                            int order_line_id = oItem.getOrder_line_id();
                            String topname = oItem.getTopname();
                            double top_price = oItem.getTop_price();
                            String bottom_name = oItem.getBottom_name();
                            double bottom_price = oItem.getBottom_price();
                            double totalprice = oItem.getTotalprice();
                            int amount = oItem.getAmount();
                            grandTotalPrice +=totalprice;
                %>

                            <tr>
                                <td><%=order_id%></td>
                                <td><%=order_line_id%></td>
                                <td><%=email%></td>
                                <td><%=amount%></td>
                                <td><%=topname%></td>
                                <td><%=top_price%></td>
                                <td><%=bottom_name%></td>
                                <td><%=bottom_price%></td>
                                <td><%=totalprice%></td>

                            </tr>

                    <%

                        }
                    } else {
                        out.println("<p>Ingen ordrer</p>");
                    }

                %>
                <tr>
                    <td colspan="8">I alt</td>
                    <td><%=grandTotalPrice%></td>
                </tr>
            </tbody>
        </table>
    </form>

    <form action="/FrontController" method="post">
        <div class="float-right">
            <input type="hidden" name="source" value="orderitemcontrol"/>
            <button type="submit" name="action" value="shop" class="btn btn-primary">Shop videre</button>
            <button type="submit" name="action" value="orders" class="btn btn-success">Tilbage</button>
        </div>
    </form>

</div>

<%@ include file="include/footer.jsp" %>

