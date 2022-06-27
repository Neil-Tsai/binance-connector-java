package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leverage extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(Leverage.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            parameters.put("leverage",20);
            String result = client.createTrade().leverage(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}
/**
 {"symbol":"BTCUSDT","leverage":20,"maxNotionalValue":"250000"}
 */

