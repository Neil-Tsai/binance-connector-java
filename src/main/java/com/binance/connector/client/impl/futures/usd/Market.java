package com.binance.connector.client.impl.futures.usd;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.JSONParser;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Market {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public Market(String baseUrl, String apiKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.showLimitUsage = showLimitUsage;
    }

    final String PING = "/fapi/v1/ping";

    public String ping() {
        return requestHandler.sendPublicRequest(baseUrl, PING, null, HttpMethod.GET, showLimitUsage);
    }

    final String TIME = "/fapi/v1/time";

    public String time() {
        return requestHandler.sendPublicRequest(baseUrl, TIME, null, HttpMethod.GET, showLimitUsage);
    }

    final String EXCHANGE_INFO = "/fapi/v1/exchangeInfo";

    public String exchangeInfo(LinkedHashMap<String,Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new BinanceConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_INFO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String DEPTH = "/fapi/v1/depth";

    public String depth(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TRADES = "/fapi/v1/trades";

    public String trades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String HISTORICAL_TRADES = "/fapi/v1/historicalTrades";

    public String historicalTrades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, HISTORICAL_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String AGG_TRADES = "/fapi/v1/aggTrades";

    public String aggTrades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, AGG_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String KLINES = "/fapi/v1/klines";

    public String klines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String CONTINUOUS_CONTRACT_KLINE = "/fapi/v1/continuousKlines";

    public String continuousContractKline(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, CONTINUOUS_CONTRACT_KLINE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INDEX_PRICE_KLINE = "/fapi/v1/indexPriceKlines";

    public String indexPriceKlines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, INDEX_PRICE_KLINE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String MARK_PRICE_KLINE = "/fapi/v1/markPriceKlines";

    public String markPriceKlines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, MARK_PRICE_KLINE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String MARK_PRICE = "/fapi/v1/premiumIndex";

    public String markPrice(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, MARK_PRICE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String FUNDING_RATE = "/fapi/v1/fundingRate";

    public String fundingRate(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendWithApiKeyRequest(baseUrl, FUNDING_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TICKER_24H = "/fapi/v1/ticker/24hr";

    public String ticker24H(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, TICKER_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TICKER_SYMBOL = "/fapi/v1/ticker/price";

    public String tickerSymbol(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, TICKER_SYMBOL, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String BOOK_TICKER = "/fapi/v1/ticker/bookTicker";

    public String bookTicker(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, BOOK_TICKER, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String OPEN_INTEREST = "/fapi/v1/openInterest";

    public String openInterest(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, OPEN_INTEREST, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String OPEN_INTEREST_HIST = "/futures/data/openInterestHist";

    public String openInterestHist(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "period" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, OPEN_INTEREST_HIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TOP_LONG_SHORT_ACCOUNT_RATIO = "/futures/data/topLongShortAccountRatio";

    public String topLongShortAccountRatio(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "period" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, TOP_LONG_SHORT_ACCOUNT_RATIO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TOP_LONG_SHORT_POSITION_RATIO = "/futures/data/topLongShortPositionRatio";

    public String topLongShortPositionRatio(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "period" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, TOP_LONG_SHORT_POSITION_RATIO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String GLOBAL_LONG_SHORT_ACCOUNT_RATIO = "/futures/data/globalLongShortAccountRatio";

    public String globalLongShortAccountRatio(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "period" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, GLOBAL_LONG_SHORT_ACCOUNT_RATIO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TAKER_LONG_SHORT_RATIO = "/futures/data/takerlongshortRatio";

    public String takerLongShortRatio(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "period" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, TAKER_LONG_SHORT_RATIO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String LVT_KLINES = "/fapi/v1/lvtKlines";

    public String lvtKlines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, LVT_KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INDEX_INFO = "/fapi/v1/indexInfo";

    public String indexInfo(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, INDEX_INFO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String ASSET_INDEX = "/fapi/v1/assetIndex";

    public String assetIndex(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, ASSET_INDEX, parameters, HttpMethod.GET, showLimitUsage);
    }
}
