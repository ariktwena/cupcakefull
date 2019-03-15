package controlLayer;

import dataLayer.OrderMapper;
import facadeLayer.BottomFacade;
import facadeLayer.CustomerFacade;
import facadeLayer.OrderFacade;
import facadeLayer.TopFacade;
import modelLayer.CartItem;
import modelLayer.Customer;

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
        String username;
        String password;
        Customer customer;

        response.setContentType("text/html;charset=UTF-8");

        ArrayList<CartItem> cart;
        cart = (ArrayList<CartItem>) session.getAttribute("cart");
        if (cart == null){
            // Insert 3 test-items in cart:
            cart = new ArrayList<>();
//            cart.add(new CartItem(1,1,1));
//            cart.add(new CartItem(2,2,2));
//            cart.add(new CartItem(3,3,3));
            session.setAttribute("cart",cart);
        }

        switch (source) {

            case "nav":
                //session.setAttribute("cart", cart);
                destination =  "/cart.jsp";
                break;
            case "customers":
                String customerID = request.getParameter("customerid");
                destination = "orders.jsp";
                break;
            case "orders":
                String orderId = request.getParameter("order_id");
                if (orderId != null) {
                    request.setAttribute("order_id", orderId);
                    destination = "orderdetails.jsp";
                } else {
                    destination = "orders.jsp";
                }
                break;
            case "orderitemcontrol":
                switch (request.getParameter("action")) {
                    case "shop":
                        destination = "index.jsp";
                        break;
                    case "orders":
                        destination = "orders.jsp";
                        break;
                }
                break;
            case "addtocart":
                String bottom = request.getParameter("bottom");
                String top = request.getParameter("top");
                String number = request.getParameter("number");

                if (bottom != null && top != null && number != null){
                    int bottomInt = Integer.parseInt(bottom);
                    int topInt = Integer.parseInt(top);
                    int numberInt = Integer.parseInt(number);
                    double topPrice = TopFacade.getTopByKey(topInt).getPrice();
                    double bottomPrice = BottomFacade.getBottomByKey(bottomInt).getPrice();
                    cart.add(new CartItem(topInt, topPrice, bottomInt, bottomPrice, numberInt));
                    request.setAttribute("status","ok");
                    request.setAttribute("message",
                            String.format("Bund: %s, Top: %s, Antal: %s er nu lagt i kurven",
                                    bottom, top, number));
                    session.setAttribute("cart",cart);
                } else {
                    request.setAttribute("status","error");
                    request.setAttribute("message",
                            String.format("Du mangler at vælge bund, top eller antal!",
                                    bottom, top, number));
                }
                destination = "/index.jsp";
                break;
            case "cart":
                int cartIndexToRemove = Integer.parseInt(request.getParameter("removeorderline"));
                cart.remove(cartIndexToRemove);
                session.setAttribute("cart", cart);
                destination = "/cart.jsp";
                break;
            case "cartcontrol":
                switch (request.getParameter("action")) {
                    case "shop":
                        destination = "index.jsp";
                        break;
                    case "pay":
                        customer = (Customer)session.getAttribute("customer");
                        double grandTotalPrice = Double.valueOf(request.getParameter("grandtotalprice"));
                        if (customer == null)
                        {
                            request.setAttribute("status","Error");
                            request.setAttribute("message","Log venligst ind for at betale");
                            destination = "/login.jsp";
                        } else {
                            // Tjek saldo
                            if (grandTotalPrice <= customer.getBalance()){
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
                                    destination = "/cashout.jsp";
                                } else {
                                    request.setAttribute("status","Error");
                                    request.setAttribute("message","Kurven er tom. Der er ikke noget at betale.");
                                }
                            } else
                            {
                                request.setAttribute("status","Error");
                                request.setAttribute("message","Du har ikke nok penge på kontoen.");
                                destination = "/cashout.jsp";
                            }
                        }
                        break;
                }
                break;
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");
                customer = facadeLayer.CustomerFacade.getCustomer(username);
                if (customer != null){
                    if (customer.getPassword().equals(password)){
                        session.removeAttribute("customer");
                        session.setAttribute("customer",customer);
                    }
                    else {
                        session.removeAttribute("customer");
                    }
                } else {
                    session.removeAttribute("customer");
                }
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
                destination = "index.jsp";
                break;
            case "logout":
                session.removeAttribute("customer");
                destination ="index.jsp";
        }

        request.getRequestDispatcher(destination).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
