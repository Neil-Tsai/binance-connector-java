package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOrder extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(GetOrder.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            parameters.put("timestamp", System.currentTimeMillis());
            //至少需要发送 orderId 与 origClientOrderId中的一个
            parameters.put("orderId", "3051922065");
            String result = client.createTrade().getOrder(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            //{"code":-2013,"msg":"Order does not exist."}
//            if (code != -2013) {
                logger.error(e.getMessage());
//            }
        }
    }
}