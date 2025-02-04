package edu.neu.csye7374.factory;

import edu.neu.csye7374.InfyStock;
import edu.neu.csye7374.Stock;


public class InfyStockFactory implements AbstractStockFactory {

    @Override
    public Stock getObject() {
        return new InfyStock();
    }
}
