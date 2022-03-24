package com.binance.connector.client.impl.futures.usd;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;

public class UserData {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public UserData(String baseUrl, String apiKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String FUTURES_U_LISTEN_KEY = "/fapi/v1/listenKey";

    public String createListenKey() {
        return requestHandler.sendWithApiKeyRequest(baseUrl, FUTURES_U_LISTEN_KEY, null, HttpMethod.POST, showLimitUsage);
    }

    public String extendListenKey() {
        return requestHandler.sendWithApiKeyRequest(baseUrl, FUTURES_U_LISTEN_KEY, null, HttpMethod.PUT, showLimitUsage);
    }

    public String closeListenKey() {
        return requestHandler.sendWithApiKeyRequest(baseUrl, FUTURES_U_LISTEN_KEY, null, HttpMethod.DELETE, showLimitUsage);
    }
}
