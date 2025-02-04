package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye7374.interfaces.Tradable;

public class Stock implements Tradable {
    private String ID;
    private String description;
    protected double price;
    private int metric;
    private List<Double> bids;
    private StockPriceStrategy priceStrategy;  // Strategy field

    public Stock() {
        this.ID = "";
        this.price = 0;
        this.description = "";
        this.bids = new ArrayList<>();
        this.bids.add(price);
        this.metric = 0;
    }

    public Stock(String ID, double price, String description) {
        this.ID = ID;
        this.price = price;
        this.description = description;
        this.bids = new ArrayList<>();
        this.bids.add(price);
        this.metric = 0;
    }

    public void setPriceStrategy(StockPriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    @Override
    public void setBid(double bid) {
        this.bids.add(bid);
        calculatePrice();  // Recalculate price whenever a new bid is added
    }

    public void calculatePrice() {
        if (priceStrategy != null) {
            double newPrice = priceStrategy.calculatePrice(bids);
            setPrice(newPrice);  
        }
    }

    public List<Double> getBid() {
        return this.bids;
    }

    @Override
    public int getMetric() {
        return this.metric;
    }

    public void setMetric(int metric) {
        this.metric = metric;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ID='" + ID + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", Metric Value=" + getMetricValue() +
                '}';
    }

    public String getMetricValue() {
        if (this.getMetric() < -3) {
            return "--> Not a good stock";
        } else if (this.getMetric() >= -3 && this.getMetric() <= 3) {
            return " --> Good stock";
        } else {
            return " --> Excellent stock";
        }
    }
}
