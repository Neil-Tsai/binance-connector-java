package com.binance.connector.client.impl;

public class BaseClientImpl {
    protected String apiKey;
    protected String secretKey;
    protected String baseUrl;
    protected boolean showLimitUsage = false;

    public void setShowLimitUsage (boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }
}
