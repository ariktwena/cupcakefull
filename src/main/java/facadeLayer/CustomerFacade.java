package facadeLayer;

import dataLayer.CustomerMapper;
import modelLayer.Customer;

import java.util.ArrayList;

public class CustomerFacade {

    public static Customer getCustomer(String username){
        return CustomerMapper.getCustomer(username);
        //return new Customer(1,"jobe@cph.dk","123","admin");
    }

    public static Customer insertCustomer(String email, String password, String userType, double balance){
        return CustomerMapper.insertCustomer(email, password, userType, balance);
    }

    public static ArrayList<Customer> getCustomerList(){
        return CustomerMapper.getCustomerList();
    }

}
