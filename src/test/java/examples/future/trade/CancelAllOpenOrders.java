package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CancelAllOpenOrders extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CancelAllOpenOrders.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            String result = client.createTrade().cancelAllOpenOrders(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}

