package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ticker24H extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(Ticker24H.class);
    public static void main(String[] args) {
        parameters.put("symbol","BTCUSDT");

        String result = client.createMarket().ticker24H(parameters);
        logger.info(result);
    }
}
