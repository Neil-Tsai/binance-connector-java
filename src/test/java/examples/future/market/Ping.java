package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ping extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(Ping.class);
    public static void main(String[] args) {

        String result = client.createMarket().ping();
        logger.info(result);
    }
}
