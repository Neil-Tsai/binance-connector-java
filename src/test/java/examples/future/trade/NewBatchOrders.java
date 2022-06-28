package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewBatchOrders extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(NewBatchOrders.class);

    public static void main(String[] args) {
        JSONArray orders = new JSONArray();

        for (int i = 0 ; i < 3 ; i++) {
            int price = 18000;
            price += i * 200;

            //買入 開多 市價單
            JSONObject order = new JSONObject();
            order.put("symbol", "BTCUSDT");
            order.put("side", "BUY");
            order.put("type", "LIMIT");
            order.put("positionSide","LONG");
            order.put("timeInForce", "GTC");
            order.put("price", String.valueOf(price));
            //btc quantity
            order.put("quantity", "0.01");
            orders.put(order);
        }

        logger.info(orders.toString());
        parameters.put("batchOrders", orders);
        String result = client.createTrade().newBatchOrders(parameters);
        logger.info(result);
    }
}
/**
 * example
 [
     {
         "type":"LIMIT",
         "timeInForce":"GTC",
         "symbol":"BTCUSDT",
         "side":"BUY",
         "price":"10001",
         "quantity":"0.001"
     }
 ]
 */
/**
 [
     {
     "orderId":3051932764,
     "symbol":"BTCUSDT",
     "status":"NEW",
     "clientOrderId":"7sbvcEiEQIup5jOFvBtHLp0",
     "price":"18000",
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
     "updateTime":1656315394862
     },
     {
     "orderId":3051932765,
     "symbol":"BTCUSDT",
     "status":"NEW",
     "clientOrderId":"ChCP09JcvxpRMmfoUpFaaf1",
     "price":"18200",
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
     "updateTime":1656315394862
     },
    {
     "orderId":3051932766,
     "symbol":"BTCUSDT",
     "status":"NEW",
     "clientOrderId":"p3MXb5AFNBfZVH8Xs7vhqH2",
     "price":"18400",
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
     "updateTime":1656315394862
    }
 ]
 */