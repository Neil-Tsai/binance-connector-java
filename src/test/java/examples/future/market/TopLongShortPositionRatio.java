package examples.future.market;

import com.binance.connector.client.enums.TimeInterval;
import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopLongShortPositionRatio extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(TopLongShortPositionRatio.class);
    public static void main(String[] args) {
        parameters.put("symbol","BTCUSDT");
        parameters.put("period", TimeInterval.FIVE_MINUTES.toString());
        parameters.put("limit", 500);
        String result = client.createMarket().topLongShortPositionRatio(parameters);
        logger.info(result);
    }
}
