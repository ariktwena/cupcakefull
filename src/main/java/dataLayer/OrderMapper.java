package dataLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderMapper {

    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet resultSet = null;

    public static void insertOrderAndLines(ArrayList<CartItem> cart, Customer customer, double grandTotalPrice){

        int orderId = 0;
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        String sqlOrderQuery = "INSERT INTO cupcake.orders(customer_id, ordre_date) values (?,?)";

        try {
            connection = ConnectionConfiguration.getConnection();

            ps = connection.prepareStatement(sqlOrderQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customer.getCustomerId());
            ps.setDate(2, date);
            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
                String sqlOrderLineQuery = "INSERT INTO cupcake.order_lines(order_id, top_id, " +
                        "bottom_id, top_price, bottom_price, amount) values (?,?,?,?,?,?)";

                for (CartItem c: cart) {
                    ps = connection.prepareStatement(sqlOrderLineQuery);
                    ps.setInt(1, orderId);
                    ps.setInt(2, c.getTopId());
                    ps.setInt(3, c.getBottomId());
                    ps.setDouble(4, c.getTopPrice());
                    ps.setDouble(5, c.getBottomPrice());
                    ps.setDouble(6,c.getAmount());
                    ps.executeUpdate();
                }
                String sqlCustomerQuery = "UPDATE cupcake.customers SET balance = balance - ? WHERE customer_id = ?";
                ps = connection.prepareStatement(sqlCustomerQuery);
                ps.setDouble(1, grandTotalPrice);
                ps.setInt(2, customer.getCustomerId());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<OrderLine> getOrderLines(int order_id){

        ArrayList<OrderLine> orderLines = null;

        int order_line_id;
        int top_id;
        int bottom_id;
        double top_price;
        double bottom_price;
        int amount;
        OrderLine ol;

        String sqlQuery = "SELECT * FROM cupcake.order_lines WHERE order_id = ?";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, order_id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (orderLines == null) {
                    orderLines = new ArrayList<>();
                }
                order_line_id = resultSet.getInt("order_line_id");
                top_id = resultSet.getInt("top_id");
                bottom_id = resultSet.getInt("bottom_id");
                top_price = resultSet.getDouble("top_price");
                bottom_price = resultSet.getDouble("bottom_price");
                amount = resultSet.getInt("amount");
                ol = new OrderLine(order_line_id, order_id, top_id, bottom_id, top_price, bottom_price, amount);
                orderLines.add(ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderLines;
    }

    public static ArrayList<Order> getOrderList(){

        ArrayList<Order> orderList = null;

        int order_id;
        int customer_id;
        String customer_email;
        Date order_date;
        Order o;

        String sqlQuery = "SELECT * FROM cupcake.orders o JOIN cupcake.customers c on o.customer_id = c.customer_id ORDER BY ordre_id DESC";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (orderList == null) {
                    orderList = new ArrayList<>();
                }
                order_id = resultSet.getInt("ordre_id");
                customer_id = resultSet.getInt("customer_id");
                customer_email = resultSet.getString("email");
                order_date = resultSet.getDate("ordre_date");
                o = new Order(order_id, customer_id, order_date, customer_email);
                orderList.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderList;
    }


    public static ArrayList<OrderItems> getOrderItemList(int orderId){

        ArrayList<OrderItems> orderItemList = null;

        int customer_id;
        String email;
        int order_line_id;
        int order_id;
        int top_id;
        String topname;
        double top_price;
        int bottom_id;
        String bottom_name;
        double bottom_price;
        double totalprice;
        int amount;
        OrderItems o;

        String sqlQuery = "SELECT * FROM cupcake.order_lines_view where order_id = ?";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1,orderId);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (orderItemList == null) {
                    orderItemList = new ArrayList<>();
                }
                order_id = resultSet.getInt("order_id");
                customer_id = resultSet.getInt("customer_id");
                email = resultSet.getString("email");
                order_line_id = resultSet.getInt("order_line_id");
                top_id = resultSet.getInt("top_id");
                topname = resultSet.getString("topname");
                top_price = resultSet.getDouble("top_price");
                bottom_id = resultSet.getInt("bottom_id");
                bottom_name = resultSet.getString("bottom_name");
                bottom_price = resultSet.getDouble("bottom_price");
                totalprice = resultSet.getDouble("totalprice");
                amount = resultSet.getInt("amount");
                o = new OrderItems(customer_id,email,order_line_id,order_id,top_id,topname,top_price,
                        bottom_id, bottom_name, bottom_price, totalprice, amount);
                orderItemList.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderItemList;
    }

}
