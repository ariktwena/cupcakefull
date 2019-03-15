package modelLayer;

public class OrderLine {

    int order_line_id;
    int order_id;
    int top_id;
    int bottom_id;
    double top_price;
    double bottom_price;
    int amount;

    public OrderLine(int order_line_id, int order_id, int top_id, int bottom_id, double top_price, double bottom_price, int amount) {
        this.order_line_id = order_line_id;
        this.order_id = order_id;
        this.top_id = top_id;
        this.bottom_id = bottom_id;
        this.top_price = top_price;
        this.bottom_price = bottom_price;
        this.amount = amount;
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

    public int getBottom_id() {
        return bottom_id;
    }

    public double getTop_price() {
        return top_price;
    }

    public double getBottom_price() {
        return bottom_price;
    }

    public int getAmount() {
        return amount;
    }
}
