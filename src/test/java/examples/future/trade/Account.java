package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    public static void main(String[] args) {
        try {
            String result = client.createTrade().account(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}
