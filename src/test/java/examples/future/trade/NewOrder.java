package examples.future.trade;

import com.binance.connector.client.enums.OrderSide;
import com.binance.connector.client.enums.OrderTypes;
import com.binance.connector.client.enums.PositionSide;
import com.binance.connector.client.enums.TimeInForce;
import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewOrder  extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(NewOrder.class);
    public static void main(String[] args) {
        //買入 開多 市價單
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", OrderSide.BUY.toString());
        parameters.put("type", OrderTypes.MARKET.toString());
        parameters.put("positionSide", PositionSide.LONG.toString());
        //btc quantity
        parameters.put("quantity", 0.01);
        parameters.put("timestamp", System.currentTimeMillis());
        String result = client.createTrade().newOrder(parameters);
        logger.info(result);
/**
 *  買入 開多 市價單
     {
         "orderId":3051904504,
         "symbol":"BTCUSDT",
         "status":"NEW",
         "clientOrderId":"acd1E1wXtkJhgGDQiJ9eJX",
         "price":"0",
         "avgPrice":"0.00000",
         "origQty":"0.010",
         "executedQty":"0",
         "cumQty":"0",
         "cumQuote":"0",
         "timeInForce":"GTC",
         "type":"MARKET",
         "reduceOnly":false,
         "closePosition":false,
         "side":"BUY",
         "positionSide":"LONG",
         "stopPrice":"0",
         "workingType":"CONTRACT_PRICE",
         "priceProtect":false,
         "origType":"MARKET",
         "updateTime":1656301907651
     }
 */

//        // 買入 開空 市價單 不能開待了解
//        parameters.put("symbol", "BTCUSDT");
//        parameters.put("side", OrderSide.BUY.toString());
//        parameters.put("type", OrderTypes.MARKET.toString());
//        parameters.put("positionSide", PositionSide.SHORT.toString());
//        //btc quantity
//        parameters.put("quantity", 0.01);
//        parameters.put("timestamp", System.currentTimeMillis());
//        String result = client.createTrade().newOrder(parameters);
//        logger.info(result);

        //買入 開多 限價單
//        parameters.put("symbol", "BTCUSDT");
//        parameters.put("side", OrderSide.BUY.toString());
//        parameters.put("type", OrderTypes.LIMIT.toString());
//        parameters.put("positionSide", PositionSide.LONG.toString());
//        parameters.put("timeInForce", TimeInForce.GTC.toString());
//        parameters.put("price", 20000);
//        //btc quantity
//        parameters.put("quantity", 0.01);
//        String result = client.createTrade().newOrder(parameters);
//        logger.info(result);
        /**
         {
             "orderId":3051922065,
             "symbol":"BTCUSDT",
             "status":"NEW",
             "clientOrderId":"vdyd6iSM6c8vfAXfaSRQ6l",
             "price":"20000",
             "avgPrice":"0.00000",
             "origQty":"0.010",
             "executedQty":"0",
             "cumQty":"0",
             "cumQuote":"0",
             "timeInForce":"GTC",
             "type":"LIMIT",
             "reduceOnly":false,
             "closePosition":false,
             "side":"BUY",
             "positionSide":"LONG",
             "stopPrice":"0",
             "workingType":"CONTRACT_PRICE",
             "priceProtect":false,
             "origType":"LIMIT",
             "updateTime":1656309614156
         }
         */
        // 賣單 平多 市價
//        parameters.put("symbol", "BTCUSDT");
//        parameters.put("side", OrderSide.SELL.toString());
//        parameters.put("type", OrderTypes.MARKET.toString());
//        parameters.put("positionSide", PositionSide.LONG.toString());
//        //btc quantity
//        parameters.put("quantity", 0.01);
//        String result = client.createTrade().newOrder(parameters);
//        logger.info(result);
    }
}