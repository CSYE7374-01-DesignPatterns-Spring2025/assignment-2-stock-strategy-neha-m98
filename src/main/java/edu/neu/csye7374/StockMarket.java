package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye7374.interfaces.Tradable;


public class StockMarket {

    private static StockMarket instance;
    private List<Tradable> stocks;

    private StockMarket() {
        stocks = new ArrayList<>();
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

    public void addStock(Tradable stock) {
        stocks.add(stock);
    }

    public void showAllStocks() {
        for (Tradable stock : stocks) {
            System.out.println(stock);
        }
    }

    public void tradeStocks(String name, double bid) {
        for (Tradable stock : stocks) {
            if (stock.getID().equals(name)) {
                stock.setBid(bid);
            }
        }
    }

    public void initializeBidding(Tradable stock, String[] bids) {
        for (String bid : bids) {
            tradeStocks(stock.getID(), Double.parseDouble(bid)); 
        }
    }
    
    public void demo() {
        // Create Bull and Bear market strategies
        StockPriceStrategy bullMarket = new BullMarketStrategy();
        StockPriceStrategy bearMarket = new BearMarketStrategy();

        // Create InfyStock and set BullMarketStrategy
        Stock infyStock = new InfyStock("INFO", 25, "Infosys Solutions");
        infyStock.setPriceStrategy(bullMarket); 
        this.addStock(infyStock);

        // Create RelianceStock and set BearMarketStrategy
        Stock relianceStock = new RelianceStock("RELI", 30, "Reliance Industries");
        relianceStock.setPriceStrategy(bearMarket); 
        this.addStock(relianceStock);

        // Show all stocks initially
        System.out.println("All stocks available in the stock market at the start:");
        showAllStocks();

        // Initialize multiple bids for InfyStock using the new method
        System.out.println("\nPlacing multiple bids on InfyStock (INFO):");
        initializeBidding(infyStock, new String[]{"21", "22.5", "28", "26", "23.5", "24"});
        System.out.println("\nAfter placing bids on InfyStock:");
        showAllStocks();

        // Initialize multiple bids for RelianceStock using the new method
        System.out.println("\nPlacing multiple bids on RelianceStock (RELI):");
        initializeBidding(relianceStock, new String[]{"30", "32", "33", "35", "31", "32"});
        System.out.println("\nAfter placing bids on RelianceStock:");
        showAllStocks();

        // Switching strategy dynamically during runtime for RelianceStock
        System.out.println("\nSwitching to Bull Market for Reliance Stock:");
        relianceStock.setPriceStrategy(bullMarket);  // Changing strategy to Bull Market
        tradeStocks("RELI", 30);  // Final bid to recalculate price with the new strategy
        showAllStocks();
    }
}    

