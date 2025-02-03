package edu.neu.csye7374;

import java.util.List;

public interface StockPriceStrategy {
    double calculatePrice(List<Double> bids);
}
