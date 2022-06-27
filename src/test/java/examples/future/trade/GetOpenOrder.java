package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOpenOrder extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(GetOpenOrder.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            //至少需要发送 orderId 与 origClientOrderId中的一个, 掛單中才會返回值
            parameters.put("orderId", "3051936935");
            String result = client.createTrade().getOpenOrder(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}