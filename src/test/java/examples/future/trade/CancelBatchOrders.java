package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CancelBatchOrders extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CancelOrder.class);

    public static void main(String[] args) {
        try {
            JSONArray list = new JSONArray();
            list.put(3051933242L);
            list.put(3051933243L);
            list.put(3051933244L);
            list.put(3051932764L);
            list.put(3051932765L);
            list.put(3051932766L);
            /**
             * orderId	LONG	NO	系统订单号
             * origClientOrderId	STRING	NO	用户自定义的订单号
             */
            parameters.put("symbol", "BTCUSDT");
            parameters.put("orderIdList", list);
            String result = client.createTrade().cancelBatchOrders(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}
