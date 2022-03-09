package com.binance.connector.client.enums;

public class DefaultUrls {
    /**
     * SPOT
     */
    public static final String PROD_URL = "https://api.binance.com";
    public static final String WS_URL = "wss://stream.binance.com:9443";
    public static final String TESTNET_URL = "https://testnet.binance.vision";
    public static final String TESTNET_WSS_URL = "wss://testnet.binance.vision";

    /**
     * USD-M
     */
    public static final String U_PROD_URL = "https://fapi.binance.com";
    public static final String U_WS_URL = "wss://fstream-auth.binance.com";
    public static final String U_TESTNET_URL = "https://testnet.binancefuture.com";
    public static final String U_TESTNET_WSS_URL = "wss://stream.binancefuture.com";

    /**
     * Coin-M
     */

    private DefaultUrls() {
    }
}
