package com.binance.connector.client.impl.futures.usd;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Trade {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public Trade(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    /*
     * 划转
     */

    /**
     * 获取划转历史
     */

    final String POSITION_SIDE_DUAL = "/fapi/v1/positionSide/dual";

    /**
     * 更改持仓模式
     * POST /fapi/v1/positionSide/dual (HMAC SHA256)
     *
     * @param parameters p
     * 名称	            类型	    是否必需	描述
     * dualSidePosition	STRING	YES	    "true": 双向持仓模式；"false": 单向持仓模式
     * recvWindow	    LONG	NO
     * timestamp	    LONG	YES
     *
     * @return string
     */
    public String changePositionSide(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "dualSidePosition", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_SIDE_DUAL, parameters, HttpMethod.POST, showLimitUsage);
    }
    /**
     * 查询持仓模式
     * GET /fapi/v1/positionSide/dual (HMAC SHA256)
     *
     * @param parameters p
     * 名称	        类型	    是否必需	描述
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     *
     * @return string
     */
    public String getPositionSide(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_SIDE_DUAL, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String MULTI_ASSSETS_MARGIN = "/fapi/v1/multiAssetsMargin";

    /**
     * 更改联合保证金模式
     * POST /fapi/v1/multiAssetsMargin (HMAC SHA256)
     *
     * @param parameters p
     * 名称	                类型	    是否必需	描述
     * multiAssetsMargin	STRING	YES	    "true": 联合保证金模式开启；"false": 联合保证金模式关闭
     * recvWindow	        LONG	NO
     * timestamp	        LONG	YES
     *
     * @return s
     */
    public String changeMultiAssetsMargin(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "multiAssetsMargin", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, MULTI_ASSSETS_MARGIN, parameters, HttpMethod.POST, showLimitUsage);
    }

    /**
     * 查询联合保证金模式
     * GET /fapi/v1/multiAssetsMargin (HMAC SHA256)
     *
     * @param parameters p
     * 名称	        类型	    是否必需	描述
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     *
     * @return s
     */
    public String queryMultiAssetsMargin(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, MULTI_ASSSETS_MARGIN, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 測試下單
     */
    final String TEST_NEW_ORDER = "/fapi/v1/order/test";
    public String testNewOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, TEST_NEW_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ORDER = "/fapi/v1/order";
    /**
     * 下单
     * POST /fapi/v1/order (HMAC SHA256)
     * 根据 order type的不同，某些参数强制要求，具体如下:
     *
     * Type	强制要求的参数
     * LIMIT	timeInForce, quantity, price
     * MARKET	quantity
     * STOP, TAKE_PROFIT	quantity, price, stopPrice
     * STOP_MARKET, TAKE_PROFIT_MARKET	stopPrice
     * TRAILING_STOP_MARKET	callbackRate
     * 条件单的触发必须:
     *
     * 如果订单参数priceProtect为true:
     * 达到触发价时，MARK_PRICE(标记价格)与CONTRACT_PRICE(合约最新价)之间的价差不能超过改symbol触发保护阈值
     * 触发保护阈值请参考接口GET /fapi/v1/exchangeInfo 返回内容相应symbol中"triggerProtect"字段
     * STOP, STOP_MARKET 止损单:
     * 买入: 最新合约价格/标记价格高于等于触发价stopPrice
     * 卖出: 最新合约价格/标记价格低于等于触发价stopPrice
     * TAKE_PROFIT, TAKE_PROFIT_MARKET 止盈单:
     * 买入: 最新合约价格/标记价格低于等于触发价stopPrice
     * 卖出: 最新合约价格/标记价格高于等于触发价stopPrice
     * TRAILING_STOP_MARKET 跟踪止损单:
     * 买入: 当合约价格/标记价格区间最低价格低于激活价格activationPrice,且最新合约价格/标记价高于等于最低价设定回调幅度。
     * 卖出: 当合约价格/标记价格区间最高价格高于激活价格activationPrice,且最新合约价格/标记价低于等于最高价设定回调幅度。
     * TRAILING_STOP_MARKET 跟踪止损单如果遇到报错 {"code": -2021, "msg": "Order would immediately trigger."}
     * 表示订单不满足以下条件:
     *
     * 买入: 指定的activationPrice 必须小于 latest price
     * 卖出: 指定的activationPrice 必须大于 latest price
     * newOrderRespType 如果传 RESULT:
     *
     * MARKET 订单将直接返回成交结果；
     * 配合使用特殊 timeInForce 的 LIMIT 订单将直接返回成交或过期拒绝结果。
     * STOP_MARKET, TAKE_PROFIT_MARKET 配合 closePosition=true:
     *
     * 条件单触发依照上述条件单触发逻辑
     * 条件触发后，平掉当时持有所有多头仓位(若为卖单)或当时持有所有空头仓位(若为买单)
     * 不支持 quantity 参数
     * 自带只平仓属性，不支持reduceOnly参数
     * 双开模式下,LONG方向上不支持BUY; SHORT 方向上不支持SELL
     */
    public String newOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    /**
     * 查询订单
     * GET /fapi/v1/order (HMAC SHA256)
     * orderId 与 origClientOrderId 必须至少发送一个
     * @param parameters p
     * @return s
     */
    public String getOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 撤销订单
     * DELETE /fapi/v1/order (HMAC SHA256)
     * orderId 与 origClientOrderId 必须至少发送一个
     * @param parameters p
     * @return s
     */
    public String cancelOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    final String BATCH_ORDERS = "/fapi/v1/batchOrders";
    /**
     * 批量下单
     * POST /fapi/v1/batchOrders (HMAC SHA256)
     * 参数:
     * 名称	        类型	    是否必需	描述
     * batchOrders	list	YES	    订单列表，最多支持5个订单
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     */
    public String newBatchOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "batchOrders", ArrayList.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, BATCH_ORDERS, parameters, HttpMethod.POST, showLimitUsage);
    }

    /**
     * 批量撤销订单
     * @param parameters p
     * @return s
     */
    public String cancelBatchOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "batchOrders", ArrayList.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, BATCH_ORDERS, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    final String ALL_OPEN_ORDERS = "/fapi/v1/allOpenOrders ";

    /**
     * 撤销全部订单
     */
    public String cancelAllOpenOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ALL_OPEN_ORDERS, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    final String OPEN_ORDER = "/fapi/v1/openOrder ";

    /**
     * 查询当前挂单
     * @param parameters p
     * @return s
     */
    public String getOpenOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, OPEN_ORDER, parameters, HttpMethod.GET, showLimitUsage);
    }

    public String cancelOpenOrder(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, OPEN_ORDERS, parameters, HttpMethod.DELETE, showLimitUsage);
    }

    final String OPEN_ORDERS = "/fapi/v1/OpenOrders ";

    /**
     * 查看当前全部挂单
     * @param parameters p
     * @return s
     */
    public String getOpenOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, OPEN_ORDERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 查询所有订单(包括历史订单)
     */
    final String ALL_ORDERS = "/api/v1/allOrders";
    public String getAllOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ALL_ORDERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 倒计时撤销所有订单
     */
    final String COUNTDOWN_CANCEL_ALL = "/fapi/v1/countdownCancelAll";
    public String countdownCancelAll(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "countdownTime", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, COUNTDOWN_CANCEL_ALL, parameters, HttpMethod.POST, showLimitUsage);
    }

    final String ACCOUNT_BALANCE = "/fapi/v2/balance";

    /**
     * 账户余额V2 (USER_DATA)
     * @param parameters p
     * @return s
     */
    public String getBalance(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ACCOUNT_BALANCE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String ACCOUNT_INFO = "/fapi/v2/account";

    /**
     * 账户信息V2 (USER_DATA)
     * @param parameters p
     * @return s
     */
    public String account(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ACCOUNT_INFO, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String LEVERAGE = "/fapi/v1/leverage";
    /**
     * 调整开仓杠杆
     * POST /fapi/v1/leverage (HMAC SHA256)
     * 参数:
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	YES	    交易对
     * leverage	    INT	    YES	    目标杠杆倍数：1 到 125 整数
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String leverage(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "leverage", Integer.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, LEVERAGE, parameters, HttpMethod.POST, showLimitUsage);
    }

    final String MARGIN_TYPE = "/fapi/v1/marginType";

    /**
     * 变换逐全仓模式
     * POST /fapi/v1/marginType (HMAC SHA256)
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	YES	    交易对
     * marginType	ENUM	YES	    保证金模式 ISOLATED(逐仓), CROSSED(全仓)
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String marginType(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "marginType", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, MARGIN_TYPE, parameters, HttpMethod.POST, showLimitUsage);
    }

    final String POSITION_MARGIN = "/fapi/v1/positionMargin";

    /**
     * 调整逐仓保证金
     * POST /fapi/v1/positionMargin (HMAC SHA256)
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	YES	    交易对
     * positionSide	ENUM	NO	    持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
     * amount	    DECIMAL	YES	    保证金资金
     * type	        INT	    YES	    调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * 只针对逐仓symbol 与 positionSide(如有)
     * @param parameters p
     * @return s
     */
    public String positionMargin(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "amount", String.class);
        ParameterChecker.checkParameter(parameters, "type", Integer.class);
        ParameterChecker.checkParameter(parameters, "timestamp", String.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_MARGIN, parameters, HttpMethod.POST, showLimitUsage);
    }

    final String POSITION_MARGIN_HISTORY = "/fapi/v1/positionMargin/history";

    /**
     * 逐仓保证金变动历史
     * GET /fapi/v1/positionMargin/history (HMAC SHA256)
     * 名称	        类型	是否必需	描述
     * symbol	    STRING	YES	交易对
     * type	        INT	    NO	调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金
     * startTime	LONG	NO	起始时间
     * endTime	    LONG	NO	结束时间
     * limit	    INT	    NO	返回的结果集数量 默认值: 500
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String getPositionMarginHistory(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_MARGIN_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String POSITION_RISK = "/fapi/v2/positionRisk";

    /**
     * 用户持仓风险V2
     * GET /fapi/v2/positionRisk
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	NO
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String positionRisk(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_RISK, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String USER_TRADES = "/fapi/v1/userTrades";

    /**
     * 账户成交历史
     * 名称	        类型	是否必需	描述
     * symbol	    STRING	YES	交易对
     * startTime	LONG	NO	起始时间
     * endTime	    LONG	NO	结束时间
     * fromId	    LONG	NO	返回该fromId及之后的成交，缺省返回最近的成交
     * limit	    INT	    NO	返回的结果集数量 默认值:500 最大值:1000.
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * GET /fapi/v1/userTrades (HMAC SHA256)
     *
     * 如果startTime 和 endTime 均未发送, 只会返回最近7天的数据。
     * startTime 和 endTime 的最大间隔为7天
     * @param parameters p
     * @return s
     */
    public String getUserTrades(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, USER_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INCOME = "/fapi/v1/income";

    /**
     * 获取账户损益资金流水
     * GET /fapi/v1/income
     * 名称	        类型	是否必需	描述
     * symbol	    STRING	NO	交易对
     * incomeType	STRING	NO	收益类型
     * startTime	LONG	NO	起始时间
     * endTime	    LONG	NO	结束时间
     * limit	    INT	    NO	返回的结果集数量 默认值:100 最大值:1000
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     *
     * incomType:
     *   "TRANSFER"划转,
     *   "WELCOME_BONUS"体验金,
     *   "REALIZED_PNL"已实现盈亏,
     *   "FUNDING_FEE"资金费用,
     *   "COMMISSION"手续费,
     *   "INSURANCE_CLEAR"爆仓清算,
     *   "REFERRAL_KICKBACK"推荐人返佣,
     *   "COMMISSION_REBATE"被推荐人返现,
     *   "DELIVERED_SETTELMENT"下架结算,
     *   "COIN_SWAP_DEPOSIT"资产转换转入,
     *   "COIN_SWAP_WITHDRAW" 资产转换转出
     *
     * 如果startTime 和 endTime 均未发送, 只会返回最近7天的数据。
     * 如果incomeType没有发送，返回所有类型账户损益资金流水。
     * "trandId" 在相同用户的同一种收益流水类型中是唯一的。
     *
     * @param parameters p
     * @return s
     */
    public String getIncome(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, INCOME, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String LEVERAGE_BRACKET = " /fapi/v1/leverageBracket";

    /**
     * 杠杆分层标准
     * GET /fapi/v1/leverageBracket
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	NO
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String leverageBracket(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, LEVERAGE_BRACKET, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String ADL_Quantile = "/fapi/v1/adlQuantile";

    /**
     * 持仓ADL队列估算
     * GET /fapi/v1/adlQuantile
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	NO
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String getADLQuantile(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, ADL_Quantile, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String FORCE_ORDERS = "/fapi/v1/forceOrders";

    /**
     * 用户强平单历史
     * GET /fapi/v1/forceOrders
     * 名称	            类型	    是否必需	描述
     * symbol	        STRING	NO
     * autoCloseType	ENUM	NO	"LIQUIDATION": 强平单, "ADL": ADL减仓单.
     * startTime	    LONG	NO
     * endTime	        LONG	NO
     * limit	        INT	    NO	Default 50; max 100.
     * recvWindow	    LONG	NO
     * timestamp	    LONG	YES
     *
     * 如果没有传 "autoCloseType", 强平单和ADL减仓单都会被返回
     * 如果没有传"startTime", 只会返回"endTime"之前7天内的数据
     *
     * @param parameters p
     * @return s
     */
    public String getForceOrders(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, FORCE_ORDERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String API_TRADING_STATUS = "/fapi/v1/apiTradingStatus";

    /**
     * 合约交易量化规则指标
     * GET /fapi/v1/apiTradingStatus
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	NO
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String getTradingStatus(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, API_TRADING_STATUS, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String COMMISSION_RATE = "/fapi/v1/commissionRate";

    /**
     * 用户手续费率
     * GET /fapi/v1/commissionRate (HMAC SHA256)
     * 名称	        类型	    是否必需	描述
     * symbol	    STRING	YES
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     * @param parameters p
     * @return s
     */
    public String getCommissionRate(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, COMMISSION_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INCOME_ASYN = "/fapi/v1/income/asyn";

    /**
     * 获取合约资金流水下载Id
     * GET /fapi/v1/income/asyn (HMAC SHA256)
     * 名称	        类型	    是否必需	描述
     * startTime	LONG	YES	    起始时间
     * endTime	    LONG	YES	    结束时间
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     *
     * 存在每月5次的请求限制，网页端和Rest接口下载次数共用。
     *
     * @param parameters p
     * @return s
     */
    public String getIncomeId(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "startTime", Long.class);
        ParameterChecker.checkParameter(parameters, "endTime", Long.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, INCOME_ASYN, parameters, HttpMethod.GET, showLimitUsage);
    }

    final String INCOME_ID = "/fapi/v1/income/asyn/id";

    /**
     * 通过下载Id获取合约资金流水下载链接
     * GET /fapi/v1/income/asyn/id (HMAC SHA256)
     *
     * @param parameters p
     * 名称	        类型	    是否必需	描述
     * downloadId	STRING	YES	    通过下载id 接口获取
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     *
     * @return s
     */
    public String getIncomeLink(LinkedHashMap<String,Object> parameters) {
        ParameterChecker.checkParameter(parameters, "downloadId", String.class);
        ParameterChecker.checkParameter(parameters, "timestamp", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, INCOME_ID, parameters, HttpMethod.GET, showLimitUsage);
    }
}
