package com.binance.connector.client.impl;

import com.binance.connector.client.FuturesUClient;
import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.futures.usd.Market;
import com.binance.connector.client.impl.futures.usd.Trade;
import com.binance.connector.client.impl.futures.usd.UserData;

public class FuturesUClientImpl extends BaseClientImpl implements FuturesUClient {

    public FuturesUClientImpl() {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = DefaultUrls.U_PROD_URL;
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
        this.baseUrl = DefaultUrls.U_PROD_URL;
    }

    public FuturesUClientImpl(String apiKey, String secretKey, String baseUrl) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public Market createMarket() {
        return new Market(baseUrl, apiKey, showLimitUsage);
    }

    @Override
    public Trade createTrade() {
        return new Trade(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public UserData createUserData() {
        return new UserData(baseUrl, apiKey, showLimitUsage);
    }
}
