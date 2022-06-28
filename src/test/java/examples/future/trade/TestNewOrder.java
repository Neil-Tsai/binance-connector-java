package examples.future.trade;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNewOrder extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(TestNewOrder.class);
    public static void main(String[] args) {
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "MARKET");
        parameters.put("positionSide", "LONG");
        parameters.put("quantity", 1);
        parameters.put("timestamp", System.currentTimeMillis());
        String result = client.createTrade().testNewOrder(parameters);
        logger.info(result);
    }
}
