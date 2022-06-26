package examples.future.market;

import com.binance.connector.client.enums.TimeInterval;
import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KLines extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(KLines.class);
    public static void main(String[] args) {
        parameters.put("symbol","BTCUSDT");
        parameters.put("interval", TimeInterval.ONE_MINUTE.toString());
        parameters.put("limit", 1500);
        String result = client.createMarket().klines(parameters);
        logger.info(result);
    }
}
