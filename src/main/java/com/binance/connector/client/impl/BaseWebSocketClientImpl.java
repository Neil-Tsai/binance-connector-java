package com.binance.connector.client.impl;

import com.binance.connector.client.utils.WebSocketCallback;
import com.binance.connector.client.utils.WebSocketConnection;

import java.util.HashMap;
import java.util.Map;

public class BaseWebSocketClientImpl {
    protected String baseUrl = "";
    protected Map<Integer, WebSocketConnection> connections = new HashMap<>();
    protected WebSocketCallback noopCallback = msg -> {};
}
