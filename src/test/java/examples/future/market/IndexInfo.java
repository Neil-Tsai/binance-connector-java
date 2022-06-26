package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexInfo extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(IndexInfo.class);
    public static void main(String[] args) {
        String result = client.createMarket().indexInfo(parameters);
        logger.info(result);
    }
}
