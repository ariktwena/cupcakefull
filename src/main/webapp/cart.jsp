<%@ page import="modelLayer.CartItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@ page import="modelLayer.Bottom" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="include/header.jsp" %>

<div class="jumbotron" style="background-color: transparent;">
    <h1>Kurv</h1>

    <form action="/FrontController" method="post">

        <input type="hidden" name="source" value="cart"/>

        <table class="table table-striped ">
            <thead>
            <tr>
                <th>Bund</th>
                <th>Top</th>
                <th>Antal</th>
                <th>Pris</th>
                <th>I alt</th>
                <th>Fjern</th>
            </tr>
            </thead>
            <tbody>

                <%
                    ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");

                    double grandTotalPrice = 0.0;
                    if (cart != null) {
                        int id = 0;
                        for (CartItem c : cart) {
                            String bottomName = BottomFacade.getBottomByKey(c.getBottomId()).getBottomName();
                            double bottomPrice = c.getBottomPrice();
                            String topName = TopFacade.getTopByKey(c.getTopId()).getTopName();
                            double topPrice = c.getTopPrice();
                            double sumPrice = bottomPrice + topPrice;
                            double totalPrice = sumPrice * c.getAmount();
                %>

                            <tr>
                                <td><%=bottomName%></td>
                                <td><%=topName%></td>
                                <td><%=c.getAmount()%></td>
                                <td><%=sumPrice%></td>
                                <td><%=totalPrice%></td>
                                <td>
                                    <button type="submit" name="removeorderline" value="<%=id%>" class="btn btn-danger btn-xs">Fjern</button>
                                </td>
                            </tr>

                    <%
                            id++;
                            grandTotalPrice += totalPrice;
                        }
                    } else {
                        out.println("<p>Kurven er tom</p>");
                    }

                %>

            <tr>
                <td colspan="4">I alt</td>
                <td><%=grandTotalPrice%></td>
                <td>&nbsp;</td>
            </tr>
            </tbody>
        </table>
    </form>

    <form action="/FrontController" method="post">
        <div class="float-right">
            <input type="hidden" name="source" value="cartcontrol"/>
            <input type="hidden" name="grandtotalprice" value="<%=grandTotalPrice%>">
            <button type="submit" name="action" value="shop" class="btn btn-primary">Shop videre</button>
            <button type="submit" name="action" value="pay" class="btn btn-success">Betal</button>
        </div>
    </form>

</div>

<%@ include file="include/footer.jsp" %>

