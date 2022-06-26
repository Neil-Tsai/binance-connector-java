package examples.future.market;

import com.binance.connector.client.enums.TimeInterval;
import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LvtKlines extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(LvtKlines.class);
    public static void main(String[] args) {
        //token name, e.g. "BTCDOWN", "BTCUP"
        parameters.put("symbol","BTCDOWN");
        parameters.put("interval", TimeInterval.FIVE_MINUTES.toString());
        parameters.put("limit", 500);
        String result = client.createMarket().lvtKlines(parameters);
        logger.info(result);
    }
}
