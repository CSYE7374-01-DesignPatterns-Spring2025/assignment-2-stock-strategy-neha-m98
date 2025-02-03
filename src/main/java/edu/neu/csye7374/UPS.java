package edu.neu.csye7374;

public class UPS extends StockAPI {
    private static final double BID_FACTOR = 1.10;
    private static final int RECENT_BID_COUNT = 3;

    public UPS(double price) {
        super("UPS", price, "UPS Common Stock");
    }

    @Override
    public void setBid(String bid) {
        double currentBid = Double.parseDouble(bid);
        double adjustedBid = currentBid * BID_FACTOR;
        bids.add(adjustedBid);
        this.calculateNewPrice();
    }

    @Override
    public int getMetric() {
        if (bids.size() <= 1) {
            return 0;
        }

        double recentAverage = calculateRecentAverage();
        double latestBid = bids.get(bids.size() - 1);
        double metricValue = latestBid - recentAverage;

        return (int)Math.round(metricValue);
    }

    private double calculateRecentAverage() {
        int start = Math.max(0, bids.size() - RECENT_BID_COUNT);
        double sum = 0;
        int count = 0;

        for (int i = start; i < bids.size(); i++) {
            sum += bids.get(i);
            count++;
        }

        return sum / count;
    }
}
