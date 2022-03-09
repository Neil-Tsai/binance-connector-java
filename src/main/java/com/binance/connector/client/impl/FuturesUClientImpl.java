package com.binance.connector.client.impl;

import com.binance.connector.client.FuturesUClient;
import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.futures.usd.Market;
import com.binance.connector.client.impl.futures.usd.Trade;
import com.binance.connector.client.impl.futures.usd.UserData;

public class FuturesUClientImpl implements FuturesUClient {

    private final String apiKey;
    private final String secretKey;
    private final String baseUrl;
    private boolean showLimitUsage = false;

    public FuturesUClientImpl() {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = DefaultUrls.PROD_URL;
    }

    public FuturesUClientImpl(String baseUrl) {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = baseUrl;
    }

    public FuturesUClientImpl(String baseUrl, boolean showLimitUsage) {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = baseUrl;
        this.showLimitUsage = showLimitUsage;
    }

    public FuturesUClientImpl(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = DefaultUrls.PROD_URL;
    }

    public FuturesUClientImpl(String apiKey, String secretKey, String baseUrl) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
    }

    public void setShowLimitUsage (boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }

    @Override
    public Market createMarket() {
        return new Market(baseUrl, apiKey, showLimitUsage);
    }

    @Override
    public Trade createTrade() {
        return null;
    }

    @Override
    public UserData createUserData() {
        return null;
    }
}
