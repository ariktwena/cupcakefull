package modelLayer;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
    int order_id;
    int customer_id;
    String email;
    Date ordre_date;

    public String getEmail() {
        return email;
    }

    public Order(int order_id, int customer_id, Date ordre_date, String email) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.email = email;
        this.ordre_date = ordre_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public Date getOrdre_date() {
        return ordre_date;
    }
}
