package com.binance.connector.client;

import com.binance.connector.client.impl.futures.usd.Market;
import com.binance.connector.client.impl.futures.usd.Trade;
import com.binance.connector.client.impl.futures.usd.UserData;

public interface FuturesUClient {
    Market createMarket();
    Trade createTrade();
    UserData createUserData();
}
