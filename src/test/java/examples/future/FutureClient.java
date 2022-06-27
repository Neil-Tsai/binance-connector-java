package examples.future;

import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.FuturesUClientImpl;
import examples.PrivateConfig;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public abstract class FutureClient {
    public static FuturesUClientImpl client = new FuturesUClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, DefaultUrls.U_TESTNET_URL);
    public static LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
}
