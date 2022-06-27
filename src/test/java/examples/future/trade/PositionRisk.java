package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionRisk extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(PositionRisk.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            String result = client.createTrade().positionRisk(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());

        }
    }
}
/**
 [
     {
     "symbol":"BTCUSDT",
     "positionAmt":"0.010",
     "entryPrice":"21396.6",
     "markPrice":"21434.68734933",
     "unRealizedProfit":"0.38087349",
     "liquidationPrice":"20518.83998788",
     "leverage":"20",
     "maxNotionalValue":"250000",
     "marginType":"isolated",
     "isolatedMargin":"11.21035761",
     "isAutoAddMargin":"false",
     "positionSide":"LONG",
     "notional":"214.34687349",
     "isolatedWallet":"10.82948412",
     "updateTime":1656319070358
     },
     {
     "symbol":"BTCUSDT",
     "positionAmt":"0.000",
     "entryPrice":"0.0",
     "markPrice":"21434.68734933",
     "unRealizedProfit":"0.00000000",
     "liquidationPrice":"0",
     "leverage":"20",
     "maxNotionalValue":"250000",
     "marginType":"isolated",
     "isolatedMargin":"0.00000000",
     "isAutoAddMargin":"false",
     "positionSide":"SHORT",
     "notional":"0",
     "isolatedWallet":"0",
     "updateTime":1656228626013
     }
 ]
 */