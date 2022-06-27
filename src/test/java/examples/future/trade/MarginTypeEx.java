package examples.future.trade;

import com.binance.connector.client.enums.MarginType;
import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarginTypeEx extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(MarginTypeEx.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            parameters.put("marginType", MarginType.ISOLATED.toString());
            String result = client.createTrade().marginType(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            if (code == -4048) {
                //有倉位不能變
            }
            logger.error(e.getMessage());

        }
    }
}
