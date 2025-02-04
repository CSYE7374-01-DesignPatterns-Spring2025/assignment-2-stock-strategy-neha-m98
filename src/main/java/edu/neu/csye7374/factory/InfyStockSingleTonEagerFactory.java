package edu.neu.csye7374.factory;

import edu.neu.csye7374.InfyStock;
import edu.neu.csye7374.Stock;

public class InfyStockSingleTonEagerFactory implements AbstractStockFactory {

    private static final InfyStockSingleTonEagerFactory instance = new InfyStockSingleTonEagerFactory();

    private InfyStockSingleTonEagerFactory() {
    }

    public static InfyStockSingleTonEagerFactory getInstance() {
        return instance;
    }

    @Override
    public Stock getObject() {
        return new InfyStock();
    }
}
