package modelLayer;

public class OrderItems {

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

    public int getAmount() {
        return amount;
    }

    public OrderItems(int customer_id, String email, int order_line_id, int order_id,
                      int top_id, String topname, double top_price, int bottom_id, String bottom_name,
                      double bottom_price, double totalprice, int amount) {
        this.customer_id = customer_id;
        this.email = email;
        this.order_line_id = order_line_id;
        this.order_id = order_id;
        this.top_id = top_id;
        this.topname = topname;
        this.top_price = top_price;
        this.bottom_id = bottom_id;
        this.bottom_name = bottom_name;
        this.bottom_price = bottom_price;
        this.totalprice = totalprice;
        this.amount = amount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getEmail() {
        return email;
    }

    public int getOrder_line_id() {
        return order_line_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getTop_id() {
        return top_id;
    }

    public String getTopname() {
        return topname;
    }

    public double getTop_price() {
        return top_price;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public String getBottom_name() {
        return bottom_name;
    }

    public double getBottom_price() {
        return bottom_price;
    }

    public double getTotalprice() {
        return totalprice;
    }
}
