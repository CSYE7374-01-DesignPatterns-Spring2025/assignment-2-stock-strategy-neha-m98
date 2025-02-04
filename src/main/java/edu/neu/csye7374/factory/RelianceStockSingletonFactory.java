package edu.neu.csye7374.factory;

import edu.neu.csye7374.RelianceStock;
import edu.neu.csye7374.Stock;

public class RelianceStockSingletonFactory implements AbstractStockFactory {
    private static RelianceStockSingletonFactory instance;

    private RelianceStockSingletonFactory() {}

    public static RelianceStockSingletonFactory getInstance() {
        if (instance == null) {
            instance = new RelianceStockSingletonFactory();
        }
        return instance;
    }

    @Override
    public Stock getObject() {
        return new RelianceStock();
    }
}