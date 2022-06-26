package examples.future.userdata;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendListenKey extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(ExtendListenKey.class);
    public static void main(String[] args) {
        String result = client.createUserData().extendListenKey();
        logger.info(result);
    }
}
