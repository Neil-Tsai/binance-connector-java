package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExchangeInfo extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeInfo.class);
    public static void main(String[] args) {

        String result = client.createMarket().exchangeInfo();
        logger.info(result);
    }
}
