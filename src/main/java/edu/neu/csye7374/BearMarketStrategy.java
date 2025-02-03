package edu.neu.csye7374;

import java.util.List;

public class BearMarketStrategy implements StockPriceStrategy {
    @Override
    public double calculatePrice(List<Double> bids) {
        if (bids.isEmpty()) {
            return 0.0;
        }

        // Calculate the average bid
        double averageBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // Calculate price drop based on fewer bids
        int numberOfBids = bids.size();
        double bidMultiplier = 1 - (0.03 * numberOfBids); 
        // Fewer bids, bigger drop (3% drop per bid)

        // Calculate new price as the average bid * multiplier
        return averageBid * bidMultiplier;
    }
}
