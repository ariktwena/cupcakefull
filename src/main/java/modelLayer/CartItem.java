package modelLayer;

import java.io.Serializable;

public class CartItem {
    int topId;
    double topPrice;
    int bottomId;
    double bottomPrice;
    int amount;

    public CartItem(int topId, double topPrice, int bottomId, double bottomPrice, int amount) {
        this.topId = topId;
        this.topPrice = topPrice;
        this.bottomId = bottomId;
        this.bottomPrice = bottomPrice;
        this.amount = amount;
    }

    public double getTopPrice() {
        return topPrice;
    }

    public double getBottomPrice() {
        return bottomPrice;
    }

    public int getTopId() {
        return topId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public int getAmount() {
        return amount;
    }
}
