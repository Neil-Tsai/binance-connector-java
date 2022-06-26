package examples.future.market;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Time extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(Time.class);
    public static void main(String[] args) {

        String result = client.createMarket().time();
        logger.info(result);
    }
}
