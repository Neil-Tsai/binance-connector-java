package examples.future.trade;

import examples.future.FutureClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBalance extends FutureClient {
    private static final Logger logger = LoggerFactory.getLogger(GetBalance.class);

    public static void main(String[] args) {
        try {
            String result = client.createTrade().getBalance(parameters);
            logger.info(result);
        } catch (Exception e) {
            JSONObject obj = new JSONObject(e.getMessage());
            int code = obj.getInt("code");
            logger.error(e.getMessage());
        }
    }
}
/**
 [
     {
     "accountAlias":"oCSgmYfWmYAu",
     "asset":"BNB",
     "balance":"2.75065411",
     "crossWalletBalance":"2.75065411",
     "crossUnPnl":"0.00000000",
     "availableBalance":"2.75065411",
     "maxWithdrawAmount":"2.75065411",
     "marginAvailable":true,
     "updateTime":1656301907651
     },
     {
     "accountAlias":"oCSgmYfWmYAu",
     "asset":"USDT",
     "balance":"2718.02414644",
     "crossWalletBalance":"2718.02414644",
     "crossUnPnl":"-1.69353531",
     "availableBalance":"2694.92929998",
     "maxWithdrawAmount":"2694.93876467",
     "marginAvailable":true,
     "updateTime":1656316800159
     },
     {
     "accountAlias":"oCSgmYfWmYAu",
     "asset":"BUSD",
     "balance":"999.66685964",
     "crossWalletBalance":"999.66685964",
     "crossUnPnl":"0.00000000",
     "availableBalance":"999.66685964",
     "maxWithdrawAmount":"999.66685964",
     "marginAvailable":true,
     "updateTime":1654797352677
     }
 ]
 */
