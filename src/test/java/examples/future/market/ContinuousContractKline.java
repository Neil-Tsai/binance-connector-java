package examples.future.market;

import com.binance.connector.client.enums.TimeInterval;

import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContinuousContractKline extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(ContinuousContractKline.class);
    public static void main(String[] args) {
        parameters.put("pair","BTCUSDT");
        //PERPETUAL 永续合约
        parameters.put("contractType", "PERPETUAL");
        parameters.put("interval", TimeInterval.ONE_MINUTE.toString());
        parameters.put("limit", 500);

        String result = client.createMarket().continuousContractKline(parameters);
        logger.info(result);
    }
}
