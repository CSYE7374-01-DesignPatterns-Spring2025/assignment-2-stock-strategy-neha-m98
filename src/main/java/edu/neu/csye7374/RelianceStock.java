// package edu.neu.csye7374;

// public class UPS extends StockAPI {
//     private static final double BID_FACTOR = 1.10;
//     private static final int RECENT_BID_COUNT = 3;

//     public UPS(double price) {
//         super("UPS", price, "UPS Common Stock");
//     }

//     @Override
//     public void setBid(String bid) {
//         double currentBid = Double.parseDouble(bid);
//         double adjustedBid = currentBid * BID_FACTOR;
//         bids.add(adjustedBid);
//         this.calculateNewPrice();
//     }

//     @Override
//     public int getMetric() {
//         if (bids.size() <= 1) {
//             return 0;
//         }

//         double recentAverage = calculateRecentAverage();
//         double latestBid = bids.get(bids.size() - 1);
//         double metricValue = latestBid - recentAverage;

//         return (int)Math.round(metricValue);
//     }

//     private double calculateRecentAverage() {
//         int start = Math.max(0, bids.size() - RECENT_BID_COUNT);
//         double sum = 0;
//         int count = 0;

//         for (int i = start; i < bids.size(); i++) {
//             sum += bids.get(i);
//             count++;
//         }

//         return sum / count;
//     }
// }
    

package edu.neu.csye7374;
public class RelianceStock extends Stock {
    public RelianceStock() {
        super();
    }

    public RelianceStock(String id, double price, String description) {
        super(id, price, description);
    }

    // Override the method to match the one in Stock
    @Override
    public void calculatePrice() {
        double average = getBid().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        if (average > this.price) {
            setMetric(getMetric() + 1);
        } else {
            setMetric(getMetric() - 1);
        }
        setPrice(average);  
    }
}
