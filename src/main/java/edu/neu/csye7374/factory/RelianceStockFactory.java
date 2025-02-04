package edu.neu.csye7374.factory;

import edu.neu.csye7374.Stock;
import edu.neu.csye7374.RelianceStock;

public class RelianceStockFactory implements AbstractStockFactory {
    @Override
    public Stock getObject() {
        return new RelianceStock();
    }
}