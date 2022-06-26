package examples.future.trade;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetPositionSide extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(GetPositionSide.class);
    public static void main(String[] args) {
        parameters.put("timestamp", System.currentTimeMillis());
        String result = client.createTrade().getPositionSide(parameters);
        logger.info(result);
    }
}
