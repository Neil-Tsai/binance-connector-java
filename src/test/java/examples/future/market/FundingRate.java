package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FundingRate extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(FundingRate.class);
    public static void main(String[] args) {
        parameters.put("symbol","BTCUSDT");

        String result = client.createMarket().fundingRate(parameters);
        logger.info(result);
    }
}