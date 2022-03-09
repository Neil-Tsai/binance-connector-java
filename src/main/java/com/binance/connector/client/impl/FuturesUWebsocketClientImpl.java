package com.binance.connector.client.impl;

import com.binance.connector.client.FuturesUWebsocketClient;
import com.binance.connector.client.enums.ContractType;
import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.enums.TimeInterval;
import com.binance.connector.client.utils.*;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FuturesUWebsocketClientImpl implements FuturesUWebsocketClient {
    private final String baseUrl;
    private final Map<Integer, WebSocketConnection> connections = new HashMap<>();
    private final WebSocketCallback noopCallback = msg -> {
    };
    private static final Logger logger = LoggerFactory.getLogger(WebsocketClientImpl.class);

    public FuturesUWebsocketClientImpl() {
        this.baseUrl = DefaultUrls.U_WS_URL;
    }

    public FuturesUWebsocketClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public int aggTradeStream(String symbol, WebSocketCallback callback) {
        return aggTradeStream(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int aggTradeStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@aggTrade", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int markPriceStream(String symbol, int speed, WebSocketCallback callback) {
        return markPriceStream(symbol, speed, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int markPriceStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@markPrice%sms", baseUrl, symbol, speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int allMarkPriceStream(int speed, WebSocketCallback callback) {
        return allMarkPriceStream(speed, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int allMarkPriceStream(int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!markPrice@arr%sms", baseUrl, speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int klineStream(String symbol, TimeInterval interval, WebSocketCallback callback) {
        return klineStream(symbol, interval, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int klineStream(String symbol, TimeInterval interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@kline_%s", baseUrl, symbol, interval.toString()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int ContinuousKline(String pair, ContractType type, TimeInterval interval, WebSocketCallback callback) {
        return ContinuousKline(pair, type, interval, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int ContinuousKline(String pair, ContractType type, TimeInterval interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s_%s@continuousKline_%s", baseUrl, pair, type.toString(), interval.toString()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int miniTickerStream(String symbol, WebSocketCallback callback) {
        return miniTickerStream(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int miniTickerStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@miniTicker", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int allMiniTickerStream(WebSocketCallback callback) {
        return allMiniTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int allMiniTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!miniTicker@arr", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int symbolTicker(String symbol, WebSocketCallback callback) {
        return symbolTicker(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int symbolTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@ticker", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int allTickerStream(WebSocketCallback callback) {
        return allTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int allTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!ticker@arr", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int bookTicker(String symbol, WebSocketCallback callback) {
        return bookTicker(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int bookTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@bookTicker", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int allBookTickerStream(WebSocketCallback callback) {
        return allBookTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int allBookTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!bookTicker", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int liquidationOrderStreams(String symbol, WebSocketCallback callback) {
        return liquidationOrderStreams(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int liquidationOrderStreams(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@forceOrder", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int allLiquidationOrderStreams(WebSocketCallback callback) {
        return allLiquidationOrderStreams(noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int allLiquidationOrderStreams(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!forceOrder@arr", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback callback) {
        return partialDepthStream(symbol, levels, speed, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@depth%s@%sms", baseUrl, symbol, levels, speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int diffDepthStream(String symbol, int speed, WebSocketCallback callback) {
        return diffDepthStream(symbol, speed, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int diffDepthStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@depth@%sms", baseUrl, symbol, speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int blvtInfoStreams(String tokenName, WebSocketCallback callback) {
        return blvtInfoStreams(tokenName,noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int blvtInfoStreams(String tokenName, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@tokenNav", baseUrl, tokenName));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int blvtNavKlineStreams(String tokenName, TimeInterval interval, WebSocketCallback callback) {
        return blvtNavKlineStreams(tokenName, interval, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int blvtNavKlineStreams(String tokenName, TimeInterval interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@nav_kline_%s", baseUrl, tokenName, interval));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int compositeIndexSymbolInfoStreams(String symbol, WebSocketCallback callback) {
        return compositeIndexSymbolInfoStreams(symbol, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int compositeIndexSymbolInfoStreams(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@compositeIndex", baseUrl, symbol));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int listenUserStream(String listenKey, WebSocketCallback callback) {
        return listenUserStream(listenKey, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int listenUserStream(String listenKey, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s", baseUrl, listenKey));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    @Override
    public int combineStreams(ArrayList<String> streams, WebSocketCallback callback) {
        return combineStreams(streams, noopCallback, callback, noopCallback, noopCallback);
    }

    @Override
    public int combineStreams(ArrayList<String> streams, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        String url = UrlBuilder.buildStreamUrl(String.format("%s/stream", baseUrl), streams);
        Request request = RequestBuilder.buildWebsocketRequest(url);
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Closes a specific stream based on stream Id.
     *
     * @param connectionId connection id
     */
    @Override
    public void closeConnection(int connectionId) {
        if (connections.containsKey(connectionId)) {
            connections.get(connectionId).close();
            logger.info("Closing Connection ID {}", connectionId);
            connections.remove(connectionId);
        } else {
            logger.info("Connection ID {} does not exist!", connectionId);
        }
    }

    /**
     * Closes all streams
     */
    @Override
    public void closeAllConnections() {
        if (!connections.isEmpty()) {
            logger.info("Closing {} connections(s)", connections.size());
            Iterator<Map.Entry<Integer, WebSocketConnection>> iter = connections.entrySet().iterator();
            while (iter.hasNext()) {
                WebSocketConnection connection = iter.next().getValue();
                connection.close();
                iter.remove();
            }
        }

        if (connections.isEmpty()) {
            HttpClientSingleton.getHttpClient().dispatcher().executorService().shutdown();
            logger.info("All connections are closed!");
        }
    }

    private int createConnection(
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback,
            Request request
    ) {
        WebSocketConnection connection = new WebSocketConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
        connection.connect();
        int connectionId = connection.getConnectionId();
        connections.put(connectionId, connection);
        return connectionId;
    }
}
