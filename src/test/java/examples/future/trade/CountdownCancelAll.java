package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountdownCancelAll extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CountdownCancelAll.class);

    public static void main(String[] args) {
        try {
            parameters.put("symbol", "BTCUSDT");
            //倒计时。 1000 表示 1 秒； 0 表示取消倒计时撤单功能。
            parameters.put("countdownTime","5000");
            String result = client.createTrade().countdownCancelAll(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}

