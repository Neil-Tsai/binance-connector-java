package examples.future.trade;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryMultiAssetsMargin extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(QueryMultiAssetsMargin.class);
    public static void main(String[] args) {
        parameters.put("timestamp", System.currentTimeMillis());
        String result = client.createTrade().queryMultiAssetsMargin(parameters);
        logger.info(result);
    }
}
