package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeMultiAssetsMargin extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(ChangeMultiAssetsMargin.class);
    public static void main(String[] args) {
        try {
            parameters.put("multiAssetsMargin", "false");
            parameters.put("timestamp", System.currentTimeMillis());
            String result = client.createTrade().changeMultiAssetsMargin(parameters);
            logger.info(result);
        } catch (Exception e) {
            //{"code":-4171,"msg":"Adjusted asset mode is currently set and does not need to be adjusted repeatedly."}
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            if (code != -4171) {
                logger.error(e.getMessage());
            }
        }
    }
}
