package examples.future.trade;

import com.binance.connector.client.enums.MarginType;
import com.binance.connector.client.enums.PositionSide;
import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionMargin extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(PositionMargin.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            parameters.put("positionSide", PositionSide.LONG);
            parameters.put("amount","100");
            parameters.put("type", 2);
            String result = client.createTrade().positionMargin(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());

        }
    }
}

