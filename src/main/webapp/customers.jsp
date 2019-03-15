<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@ page import="facadeLayer.OrderFacade" %>
<%@ page import="modelLayer.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="facadeLayer.CustomerFacade" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="include/header.jsp" %>

<div class="jumbotron" style="background-color: transparent;">
    <h1>Kunder</h1>

    <form action="/FrontController" method="post">

        <input type="hidden" name="source" value="customers"/>

        <table class="table table-striped ">
            <thead>
            <tr>
                <th>Kunde Nr</th>
                <th>Email</th>
                <th>Kodeord</th>
                <th>Saldo</th>
                <th>Antal ordrer</th>
                <th>Ordrer</th>
            </tr>
            </thead>
            <tbody>

                <%
                    ArrayList<Customer> customerList = CustomerFacade.getCustomerList();

                    if (customerList != null) {
                        for (Customer c: customerList) {
                            int customer_id = c.getCustomerId();
                            String email = c.getEmail();
                            String kodeord = c.getPassword();
                            double saldo = c.getBalance();
                            int numberOfOrders = c.getNumberOfOrders();

                %>
                            <tr>
                                <td><%=customer_id%></td>
                                <td><%=email%></td>
                                <td><%=kodeord%></td>
                                <td><%=saldo%></td>
                                <td><%=numberOfOrders%></td>
                                <td>
                                    <button type="submit" name="customerid" value="<%=customer_id%>" class="btn btn-success btn-xs">Se ordrer</button>
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

