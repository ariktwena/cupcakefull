package modelLayer;

public class Bottom {
    int bottomId;
    String bottomName;
    double price;

    public Bottom(int bottomId, String bottomName, double price) {
        this.bottomId = bottomId;
        this.bottomName = bottomName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public String getBottomName() {
        return bottomName;
    }
}
