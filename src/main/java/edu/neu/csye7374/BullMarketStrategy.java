package edu.neu.csye7374;

import java.util.Collections;
import java.util.List;

public class BullMarketStrategy implements StockPriceStrategy {
    @Override
    public double calculatePrice(List<Double> bids) {
        if (bids.isEmpty()) {
            return 0.0;
        }

        // Get the highest bid
        double highestBid = Collections.max(bids);

        // Calculate price change based on number of bids
        int numberOfBids = bids.size();
        double bidMultiplier = 1 + (0.05 * numberOfBids); 
        // More bids, more price rise (5% per bid)

        return highestBid * bidMultiplier;
    }
}
