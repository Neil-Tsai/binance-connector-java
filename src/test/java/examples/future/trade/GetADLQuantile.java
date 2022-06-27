package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetADLQuantile extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(GetADLQuantile.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            String result = client.createTrade().getADLQuantile(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}
/** {"symbol":"BTCUSDT","adlQuantile":{"LONG":2,"SHORT":0,"BOTH":0}} */