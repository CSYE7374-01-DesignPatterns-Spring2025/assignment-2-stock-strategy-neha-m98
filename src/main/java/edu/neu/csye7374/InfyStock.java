// package edu.neu.csye7374;

// import java.util.Collections;

// public class INTC extends StockAPI{

//     public INTC(double price) {
//         super("INTC", price, "Intel Common Stock");
//     }

//     @Override
//     public int getMetric() {
//         if (bids.size() <= 1) {
//             return 0;
//         }
//         double minBid = Collections.min(bids);
//         double maxBid = Collections.max(bids);
// // 
//         double volatility = maxBid - minBid;
//         double priceChange = bids.get(bids.size() - 1) - bids.get(0);

//         double currentPrice = getPrice();

//         return (int)Math.round((volatility * currentPrice) / priceChange);
//     }
// }

package edu.neu.csye7374;

public class InfyStock extends Stock {


	public InfyStock() {
		super();
	}

	public InfyStock(String name, double price, String description) {
		super(name, price, description);
	}

	@Override
	public void calculatePrice() {
		double avg = 0;
		for(double p : getBid()) {
			avg+= p;
		}
		avg = avg/getBid().size();
		if(avg > this.price && getMetric() >= 0) {
			setMetric(getMetric() +1);
		}else if(avg > this.price && getMetric() < 0){
			setMetric(0);
		}else if(avg < this.price && getMetric() <= 0) {
			setMetric(getMetric()-1);
		}else if(avg < this.price && getMetric() > 0) {
			setMetric(0);
		}
		setPrice(avg);
	}
}

