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
    /**
     * Test connectivity to the Rest API.
     * <br><br>
     * GET /fapi/v1/ping
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/cn/#ping">...</a>
     */
    public String ping() {
        return requestHandler.sendPublicRequest(baseUrl, PING, null, HttpMethod.GET, showLimitUsage);
    }

    final String TIME = "/fapi/v1/time";
    /**
     * Test connectivity to the Rest API and get the current server time.
     * <br><br>
     * GET /api/v3/time
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#test-connectivity">
     *     https://binance-docs.github.io/apidocs/spot/en/#check-server-time</a>
     */
    public String time() {
        return requestHandler.sendPublicRequest(baseUrl, TIME, null, HttpMethod.GET, showLimitUsage);
    }

    final String EXCHANGE_INFO = "/fapi/v1/exchangeInfo";
    /**
     * Current exchange trading rules and symbol information.
     * <br><br>
     * GET /api/v3/exchangeinfo
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string <br>
     * symbols -- optional/string
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#exchange-information">
     *     https://binance-docs.github.io/apidocs/spot/en/#exchange-information</a>
     */
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
    /**
     * GET /api/v3/depth
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the results
     *            Default 100; max 5000. Valid limits:[5, 10, 20, 50, 100, 500, 1000, 5000] <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#order-book">
     *     https://binance-docs.github.io/apidocs/spot/en/#order-book</a>
     */
    public String depth(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TRADES = "/fapi/v1/trades";
    /**
     * Get recent trades.
     * <br><br>
     * GET /api/v3/trades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#recent-trades-list">
     *     https://binance-docs.github.io/apidocs/spot/en/#recent-trades-list</a>
     */
    public String trades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String HISTORICAL_TRADES = "/fapi/v1/historicalTrades";
    /**
     * Get older market trades.
     * <br><br>
     * GET /api/v3/historicalTrades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the result Default 500; max 1000 <br>
     * fromId -- optional/long -- trade id to fetch from. Default gets most recent trades <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#old-trade-lookup">
     *     https://binance-docs.github.io/apidocs/spot/en/#old-trade-lookup</a>
     *
     */
    public String historicalTrades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, HISTORICAL_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String AGG_TRADES = "/fapi/v1/aggTrades";
    /**
     * Get compressed, aggregate trades. Trades that fill at the time, from the same order,
     * with the same price will have the quantity aggregated.
     * <br><br>
     * GET /api/v3/aggTrades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * fromId -- optional/long -- id to get aggregate trades from INCLUSIVE <br>
     * startTime -- optional/long -- Timestamp in ms to get aggregate trades from INCLUSIVE <br>
     * endTime -- optional/long -- Timestamp in ms to get aggregate trades until INCLUSIVE <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#compressed-aggregate-trades-list">
     *     https://binance-docs.github.io/apidocs/spot/en/#compressed-aggregate-trades-list</a>
     */
    public String aggTrades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, AGG_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String KLINES = "/fapi/v1/klines";
    /**
     * Kline/candlestick bars for a symbol.
     * Klines are uniquely identified by their open time.
     * <br><br>
     * GET /api/v3/klines
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * interval -- mandatory/string <br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#kline-candlestick-data">
     *     https://binance-docs.github.io/apidocs/spot/en/#kline-candlestick-data</a>
     */
    public String klines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String CONTINUOUS_CONTRACT_KLINE = "/fapi/v1/continuousKlines";
    /**
     * Kline/candlestick bars for a specific contract type.
     * Klines are uniquely identified by their open time.
     * GET /fapi/v1/continuousKlines
     * @param parameters LinkedHashedMap of String,Object pair where String is the name of the parameter and Object is the value of the parameter
     * @return String
     *
     * pair	-- mandatory/string
     * contractType	-- mandatory/ENUM
     * interval	-- mandatory/ENUM
     * startTime -- optional/LONG
     * endTime -- optional/LONG
     * limit -- optional/INT -- Default 500; max 1500.
     */
    public String continuousContractKline(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, CONTINUOUS_CONTRACT_KLINE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INDEX_PRICE_KLINE = "/fapi/v1/indexPriceKlines";
    /**
     *Kline/candlestick bars for the index price of a pair.
     * Klines are uniquely identified by their open time.
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#index-price-kline-candlestick-data">...</a>
     */
    public String indexPriceKlines(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol" ,String.class);
        ParameterChecker.checkParameter(parameters, "interval" ,String.class);
        return requestHandler.sendPublicRequest(baseUrl, INDEX_PRICE_KLINE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String MARK_PRICE_KLINE = "/fapi/v1/markPriceKlines";
    /**
     *Kline/candlestick bars for the index price of a pair.
     * Klines are uniquely identified by their open time.
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#mark-price-kline-candlestick-data">...</a>
     */
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
    /**
     * 24 hour rolling window price change statistics. Careful when accessing this with no symbol.
     * <br><br>
     * GET /api/v3/ticker/24hr
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics">
     *     https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics</a>
     */
    public String ticker24H(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, TICKER_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String TICKER_SYMBOL = "/fapi/v1/ticker/price";
    /**
     * Latest price for a symbol or symbols.
     * <br><br>
     * GET /api/v3/ticker/price
     * <br>
     * https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#symbol-price-ticker">
     *     https://binance-docs.github.io/apidocs/spot/en/#symbol-price-ticker</a>
     */
    public String tickerSymbol(LinkedHashMap<String,Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, TICKER_SYMBOL, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String BOOK_TICKER = "/fapi/v1/ticker/bookTicker";
    /**
     * Best price/qty on the order book for a symbol or symbols.
     * <br><br>
     * GET /api/v3/ticker/bookTicker
     * <br>
     * https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#symbol-order-book-ticker">
     *     https://binance-docs.github.io/apidocs/spot/en/#symbol-order-book-ticker</a>
     */
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

    /**
     * token name, e.g. "BTCDOWN", "BTCUP"
     * @param parameters p
     * @return string
     */
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
