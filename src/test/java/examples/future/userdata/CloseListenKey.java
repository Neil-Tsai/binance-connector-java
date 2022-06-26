package examples.future.userdata;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseListenKey extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CloseListenKey.class);
    public static void main(String[] args) {
        String result = client.createUserData().closeListenKey();
        logger.info(result);
    }
}
