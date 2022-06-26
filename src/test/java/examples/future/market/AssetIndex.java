package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetIndex extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(AssetIndex.class);
    public static void main(String[] args) {

        String result = client.createMarket().assetIndex(parameters);
        logger.info(result);
    }
}
