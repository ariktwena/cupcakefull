package dataLayer;

import modelLayer.Customer;
import modelLayer.Top;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMapper {

    private static int customerId = 0;
    private static String email = "";
    private static String password = "";
    private static String userType = "customer";
    private static double balance = 0.0;
    private static int numberOfOrders;
    private static Connection connection = null;
    private static Customer customer = null;
    private static PreparedStatement ps = null;
    private static ResultSet resultSet = null;

    public static Customer getCustomer(String customerEmail){

        String sqlQuery = "SELECT customer_id, email, password, usertype, balance " +
                " FROM cupcake.customers WHERE email = ?";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, customerEmail);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                userType = resultSet.getString("usertype");
                balance = resultSet.getDouble("balance");
                customer = new Customer(customerId, email, password, userType, balance, 0);
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
        return customer;
    }

    public static Customer insertCustomer(String email, String password, String userType, double balance){

        String sqlQuery = "INSERT INTO cupcake.customers(email,password,usertype, balance) values (?,?,?,?)";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, userType);
            ps.setDouble(4, balance);
            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                customerId = resultSet.getInt(1);
                customer = new Customer(customerId, email, password, userType, balance, 0);
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
        return customer;
    }

    public static ArrayList<Customer> getCustomerList(){

        ArrayList<Customer> customerList = null;
        String sqlQuery = "SELECT * FROM cupcake.customer_view";

        try {
            connection = ConnectionConfiguration.getConnection();

            ps = connection.prepareStatement(sqlQuery);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (customerList == null){
                    customerList = new ArrayList<>();
                }
                customerId = resultSet.getInt("customer_id");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                userType = resultSet.getString("usertype");
                balance = resultSet.getDouble("balance");
                numberOfOrders = resultSet.getInt("numberoforders");
                customer = new Customer(customerId, email, password, userType, balance, numberOfOrders);
                customerList.add(customer);
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
        return customerList;
    }
}
