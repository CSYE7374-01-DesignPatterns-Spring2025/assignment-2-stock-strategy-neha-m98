package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class StockAPI implements Tradable {
    private String name;
    private double price;
    private String description;
    public List<Double> bids = new ArrayList<>();
    private StockPriceStrategy stockPriceStrategy;

    public StockAPI(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceStrategy(StockPriceStrategy stockPriceStrategy) {
        this.stockPriceStrategy = stockPriceStrategy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock: ").append(name);
        sb.append(String.format(", Current Price: %.2f", price));
        sb.append(", Description: ").append(description);
        int currentMetric = getMetric();
        if (getMetric() > 0) {
            sb.append(String.format(", Metric: Positive metric of %s", currentMetric));
        } else if (getMetric() < 0) {
            sb.append(String.format(", Metric: Negative metric of %s", currentMetric));
        } else {
            sb.append(", Metric: Stable performance of Stock");
        }

        return sb.toString();
    }

    @Override
    public void setBid(String bid) {
        bids.add(Double.parseDouble(bid));
        this.calculateNewPrice();
    }

    public void calculateNewPrice() {
        double newPrice = this.stockPriceStrategy.calculatePrice(bids);
        this.setPrice(newPrice);
    }

    @Override
    public int getMetric() {
        if (bids.size() <= 1) {
            return 0;
        }

        double sum = 0;
        for (Double price : bids) {
            sum += price;
        }

        double averagePrice = sum / bids.size();
        double latestBid = bids.get(bids.size() - 1);
        double metricValue = latestBid - averagePrice;

        return (int) Math.round(metricValue);
    }
}
