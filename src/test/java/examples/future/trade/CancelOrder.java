package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CancelOrder extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CancelOrder.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            parameters.put("timestamp", System.currentTimeMillis());
            /**
             * orderId	LONG	NO	系统订单号
             * origClientOrderId	STRING	NO	用户自定义的订单号
             */
            parameters.put("orderId", "3051922065");
            String result = client.createTrade().cancelOrder(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}