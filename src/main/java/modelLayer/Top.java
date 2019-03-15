package modelLayer;

public class Top {
    int topId;
    String topName;
    double price;

    public Top(int topId, String topName, double price) {
        this.topId = topId;
        this.topName = topName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getTopId() {
        return topId;
    }

    public String getTopName() {
        return topName;
    }
}
