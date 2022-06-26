package com.binance.connector.client;

import com.binance.connector.client.enums.ContractType;
import com.binance.connector.client.enums.TimeInterval;
import com.binance.connector.client.utils.WebSocketCallback;

import java.sql.Time;
import java.util.ArrayList;

public interface FuturesUWebsocketClient {
    int aggTradeStream(String symbol, WebSocketCallback callback);
    int aggTradeStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int markPriceStream(String symbol, int speed, WebSocketCallback callback);
    int markPriceStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int allMarkPriceStream(int speed, WebSocketCallback callback);
    int allMarkPriceStream(int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int klineStream(String symbol, TimeInterval interval, WebSocketCallback callback);
    int klineStream(String symbol, TimeInterval interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int ContinuousKline(String pair, ContractType type, TimeInterval interval, WebSocketCallback callback);
    int ContinuousKline(String pair, ContractType type, TimeInterval interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int miniTickerStream(String symbol, WebSocketCallback callback);
    int miniTickerStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int allMiniTickerStream(WebSocketCallback callback);
    int allMiniTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int symbolTicker(String symbol, WebSocketCallback callback);
    int symbolTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int allTickerStream(WebSocketCallback callback);
    int allTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int bookTicker(String symbol, WebSocketCallback callback);
    int bookTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int allBookTickerStream(WebSocketCallback callback);
    int allBookTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int liquidationOrderStreams(String symbol, WebSocketCallback callback);
    int liquidationOrderStreams(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int allLiquidationOrderStreams(WebSocketCallback callback);
    int allLiquidationOrderStreams(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback callback);
    int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int diffDepthStream(String symbol, int speed, WebSocketCallback callback);
    int diffDepthStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
//    int blvtInfoStreams(String tokenName, WebSocketCallback callback);
//    int blvtInfoStreams(String tokenName, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
//    int blvtNavKlineStreams(String tokenName, TimeInterval speed, WebSocketCallback callback);
//    int blvtNavKlineStreams(String tokenName, TimeInterval speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int compositeIndexSymbolInfoStreams(String symbol, WebSocketCallback callback);
    int compositeIndexSymbolInfoStreams(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int listenUserStream(String listenKey, WebSocketCallback callback);
    int listenUserStream(String listenKey, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    int combineStreams(ArrayList<String> streams, WebSocketCallback callback);
    int combineStreams(ArrayList<String> streams, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback);
    void closeConnection(int streamId);
    void closeAllConnections();
}
