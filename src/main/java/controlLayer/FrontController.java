package controlLayer;

import dataLayer.OrderMapper;
import facadeLayer.BottomFacade;
import facadeLayer.CustomerFacade;
import facadeLayer.OrderFacade;
import facadeLayer.TopFacade;
import modelLayer.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String destination = "index.jsp";
        String source = request.getParameter("source");
        if (source == null){
            source = "initialize";
        }
        String username;
        String password;
        Customer customer;
        ArrayList<Top> topList;
        ArrayList<Bottom> bottomList;

        response.setContentType("text/html;charset=UTF-8");

        ArrayList<CartItem> cart;
        cart = (ArrayList<CartItem>) session.getAttribute("cart");
        if (cart == null){
            cart = new ArrayList<>();
            session.setAttribute("cart",cart);
        }

        try {

            switch (source) {

                case "initialize":
                    topList = TopFacade.getTopList();
                    bottomList = BottomFacade.getBottomList();
                    request.setAttribute("toplist", topList);
                    request.setAttribute("bottomlist", bottomList);
                    destination = "index.jsp";
                    break;
                case "nav":
                    switch (request.getParameter("action")) {
                        case "cart":
                            destination = "WEB-INF/cart.jsp";
                            break;
                        case "login":
                            destination = "WEB-INF/login.jsp";
                            break;
                        case "customers":
                            destination = "WEB-INF/customers.jsp";
                            break;
                    }
                    break;
                case "customers":
                    String customerId = request.getParameter("customerid");
                    request.setAttribute("customerid", customerId);
                    destination = "WEB-INF/orders.jsp";
                    break;
                case "orders":
                    String orderId = request.getParameter("order_id");
                    if (orderId != null) {
                        request.setAttribute("order_id", orderId);
                        destination = "WEB-INF/orderdetails.jsp";
                    } else {
                        destination = "WEB-INF/orders.jsp";
                    }
                    break;
                case "orderitemcontrol":
                    switch (request.getParameter("action")) {
                        case "shop":
                            topList = TopFacade.getTopList();
                            bottomList = BottomFacade.getBottomList();
                            request.setAttribute("toplist", topList);
                            request.setAttribute("bottomlist", bottomList);
                            destination = "index.jsp";
                            break;
                        case "orders":
                            destination = "WEB-INF/orders.jsp";
                            break;
                    }
                    break;
                case "addtocart":
                    String bottom = request.getParameter("bottom");
                    String top = request.getParameter("top");
                    String number = request.getParameter("number");

                    if (bottom != null && top != null && number != null) {
                        int bottomInt = Integer.parseInt(bottom);
                        int topInt = Integer.parseInt(top);
                        int numberInt = Integer.parseInt(number);
                        double topPrice = TopFacade.getTopByKey(topInt).getPrice();
                        double bottomPrice = BottomFacade.getBottomByKey(bottomInt).getPrice();
                        cart.add(new CartItem(topInt, topPrice, bottomInt, bottomPrice, numberInt));
                        request.setAttribute("status", "ok");
                        request.setAttribute("message",
                                String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                        bottom, top, number));
                        session.setAttribute("cart", cart);
                    } else {
                        request.setAttribute("status", "error");
                        request.setAttribute("message",
                                String.format("Du mangler at vælge bund, top eller antal!",
                                        bottom, top, number));
                    }
                    topList = TopFacade.getTopList();
                    bottomList = BottomFacade.getBottomList();
                    request.setAttribute("toplist", topList);
                    request.setAttribute("bottomlist", bottomList);
                    destination = "index.jsp";
                    break;
                case "cart":
                    int cartIndexToRemove = Integer.parseInt(request.getParameter("removeorderline"));
                    cart.remove(cartIndexToRemove);
                    session.setAttribute("cart", cart);
                    destination = "WEB-INF/cart.jsp";
                    break;
                case "cartcontrol":
                    switch (request.getParameter("action")) {
                        case "shop":
                            topList = TopFacade.getTopList();
                            bottomList = BottomFacade.getBottomList();
                            request.setAttribute("toplist", topList);
                            request.setAttribute("bottomlist", bottomList);
                            destination = "index.jsp";
                            break;
                        case "pay":
                            customer = (Customer) session.getAttribute("customer");
                            double grandTotalPrice = Double.valueOf(request.getParameter("grandtotalprice"));
                            if (customer == null) {
                                request.setAttribute("status", "Error");
                                request.setAttribute("message", "Log venligst ind for at betale");
                                destination = "WEB-INF/login.jsp";
                            } else {
                                // Tjek saldo
                                if (grandTotalPrice <= customer.getBalance()) {
                                    if (cart.size() > 0) {
                                        // Hvis der er penge nok, så gem ordren og træk pengene
                                        OrderFacade.insertOrderAndLines(cart, customer, grandTotalPrice);
                                        // Træk pengene
                                        customer.setBalance(customer.getBalance() - grandTotalPrice);
                                        // Opdater saldo og tøm kurv
                                        session.removeAttribute("cart");
                                        session.removeAttribute("customer");
                                        session.setAttribute("customer", customer);
                                        request.setAttribute("status", "OK");
                                        request.setAttribute("message", "Så har du betalt.");
                                        destination = "WEB-INF/cashout.jsp";
                                    } else {
                                        request.setAttribute("status", "Error");
                                        request.setAttribute("message", "Kurven er tom. Der er ikke noget at betale.");
                                    }
                                } else {
                                    request.setAttribute("status", "Error");
                                    request.setAttribute("message", "Du har ikke nok penge på kontoen.");
                                    destination = "WEB-INF/cashout.jsp";
                                }
                            }
                            break;
                    }
                    break;
                case "login":
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    customer = facadeLayer.CustomerFacade.getCustomer(username);
                    if (customer != null) {
                        if (customer.getPassword().equals(password)) {
                            session.removeAttribute("customer");
                            session.setAttribute("customer", customer);
                        } else {
                            session.removeAttribute("customer");
                        }
                    } else {
                        session.removeAttribute("customer");
                    }
                    topList = TopFacade.getTopList();
                    bottomList = BottomFacade.getBottomList();
                    request.setAttribute("toplist", topList);
                    request.setAttribute("bottomlist", bottomList);

                    destination = "index.jsp";
                    break;
                case "register":
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    customer = CustomerFacade.insertCustomer(username, password, "customer", 100.0);
                    // TODO: Need to check if user already exist before inserting and then log in
                    // TODO: Check if password1 and 2 are the same
                    if (customer != null) {
                        session.removeAttribute("customer");
                        session.setAttribute("customer", customer);
                    }
                    topList = TopFacade.getTopList();
                    bottomList = BottomFacade.getBottomList();
                    request.setAttribute("toplist", topList);
                    request.setAttribute("bottomlist", bottomList);

                    destination = "index.jsp";
                    break;
                case "logout":
                    topList = TopFacade.getTopList();
                    bottomList = BottomFacade.getBottomList();
                    request.setAttribute("toplist", topList);
                    request.setAttribute("bottomlist", bottomList);

                    session.removeAttribute("customer");
                    destination = "index.jsp";
            }

            request.getRequestDispatcher(destination).forward(request, response);

        } catch (CupCakeException e){
            request.setAttribute("message",e.getMessage());
            destination = "WEB-INF/error.jsp";
            request.getRequestDispatcher(destination).forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
