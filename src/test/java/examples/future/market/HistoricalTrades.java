package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoricalTrades extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(HistoricalTrades.class);
    public static void main(String[] args) {
        parameters.put("symbol","BTCUSDT");
        parameters.put("limit", 500);
        String result = client.createMarket().trades(parameters);
        logger.info(result);
    }
}
