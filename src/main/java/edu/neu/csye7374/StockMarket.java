package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private static StockMarket instance;
    private List<StockAPI> stocks = new ArrayList<>();

    private StockMarket() {
    }

    public static StockMarket getInstance() {
        if (instance == null) {
            synchronized (StockMarket.class) {
                if (instance == null) {
                    instance = new StockMarket();
                }
            }
        }

        return instance;
    }

    public void addStock(StockAPI stock) {
        stocks.add(stock);
    }

    public boolean removeStock(StockAPI stock) {
        return stocks.remove(stock);
    }

    public void displayAllStocks() {
        stocks.forEach(stock -> System.out.println(stock.toString()));
    }

    public void tradeStock(StockAPI stock, String bid) {
        stock.setBid(bid);
        System.out.println(stock);
    }

    public void initializeBiding(StockAPI stock, String[] bids) {
        for (String bid : bids) {
            tradeStock(stock, bid);
        }
    }

    public static void demo() {
        StockMarket stockMarket = StockMarket.getInstance();
        StockPriceStrategy bullMarket = new BullMarketStrategy();
        StockPriceStrategy bearMarket = new BearMarketStrategy();
       
        System.out.println("======= PLTR Stock Bid Start ======= ");
        StockAPI stock = new StockAPI("PLTR", 81, "PLTR Common Stock");
        stock.setPriceStrategy(bullMarket);
        stockMarket.addStock(stock);
        stockMarket.initializeBiding(stock, new String[]{"79", "82", "85", "80", "83"});
        System.out.println("\n");

        System.out.println("======= UPS Stock Bid Start ======= ");
        StockAPI upsStock = new UPS(114);
        upsStock.setPriceStrategy(bullMarket);
        stockMarket.addStock(upsStock);
        stockMarket.initializeBiding(upsStock, new String[]{"110", "115", "120", "112", "118"});
        System.out.println("\n");

        System.out.println("======= INTEL Stock Bid Start ======= ");
        StockAPI intelStock = new INTC(20);
        intelStock.setPriceStrategy(bearMarket);
        stockMarket.addStock(intelStock);
        stockMarket.initializeBiding(intelStock, new String[]{"19", "21", "22", "20", "23"});
        System.out.println("\n");

        System.out.println("======= All Stock Information ======= ");
        StockMarket.getInstance().displayAllStocks();
        System.out.println("\n");
    }
}
