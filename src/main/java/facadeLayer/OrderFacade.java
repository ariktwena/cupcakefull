package facadeLayer;

import dataLayer.OrderMapper;
import modelLayer.CartItem;
import modelLayer.Customer;
import modelLayer.Order;
import modelLayer.OrderItems;

import java.util.ArrayList;

public class OrderFacade {

    public static void insertOrderAndLines(ArrayList<CartItem> cart, Customer customer, double grandTotalPrice) {
        OrderMapper.insertOrderAndLines(cart, customer, grandTotalPrice);
    }

    public static ArrayList<Order> getOrderList() {
        return OrderMapper.getOrderList();
    }

    public static ArrayList<Order> getOrderListByCustomerId(int customer_id) {
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Order> orderList = OrderMapper.getOrderList();
        for (Order o: orderList){
            if (o.getCustomer_id() == customer_id){
                oList.add(o);
            }
        }
        return oList;
    }

    public static ArrayList<OrderItems> getOrderItemListByOrderId(int orderId) {
        return OrderMapper.getOrderItemList(orderId);
    }

}
