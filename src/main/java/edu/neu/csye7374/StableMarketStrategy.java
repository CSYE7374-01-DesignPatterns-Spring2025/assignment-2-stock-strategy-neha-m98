package edu.neu.csye7374;

import java.util.List;

public class StableMarketStrategy implements StockPriceStrategy {
    @Override
    public double calculatePrice(List<Double> bids) {
        if (bids.isEmpty()) {
            return 0.0;
        }

        double averageBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        double adjustmentFactor = 1.02; 
        return averageBid * adjustmentFactor;
    }
}