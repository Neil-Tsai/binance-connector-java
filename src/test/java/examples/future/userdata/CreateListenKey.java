package examples.future.userdata;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateListenKey extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(CreateListenKey.class);
    public static void main(String[] args) {
        String result = client.createUserData().createListenKey();
        logger.info(result);
    }
}
