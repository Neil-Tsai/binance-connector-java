package examples.future.trade;

import com.binance.connector.client.enums.OrderSide;
import com.binance.connector.client.enums.OrderTypes;
import com.binance.connector.client.enums.PositionSide;
import examples.future.FutureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNewOrder extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(TestNewOrder.class);
    public static void main(String[] args) {
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", OrderSide.BUY.toString());
        parameters.put("type", OrderTypes.MARKET.toString());
        parameters.put("positionSide", PositionSide.LONG.toString());
        parameters.put("quantity", 1);
        parameters.put("timestamp", System.currentTimeMillis());
        String result = client.createTrade().testNewOrder(parameters);
        logger.info(result);
    }
}
