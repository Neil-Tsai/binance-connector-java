package examples.future.trade;

import com.binance.connector.client.enums.DualSidePosition;
import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangePositionSide extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(ChangePositionSide.class);
    public static void main(String[] args) {
        try {
            parameters.put("dualSidePosition", DualSidePosition.DOUBLE.toString());
            parameters.put("timestamp", System.currentTimeMillis());
            String result = client.createTrade().changePositionSide(parameters);
            logger.info(result);
        } catch (Exception e) {
            /**
             * -4059倉位不需改變
             *  {"code":-4059,"msg":"No need to change position side."}
             */
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            if (code != -4059) {
                logger.error(e.getMessage());
            }

        }
    }
}
