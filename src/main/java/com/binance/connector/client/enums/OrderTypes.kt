package com.binance.connector.client.enums

/**
 * 订单种类 (orderTypes, type)
 */
enum class OrderTypes(val code: String) {
    /** 限價單 */
    LIMIT("LIMIT"),
    /** 市價單 */
    MARKET("MARKET"),
    /** 止损限价单 */
    STOP("STOP"),
    /** 止损市价单 */
    STOP_MARKET("STOP_MARKET"),
    /** 止盈限价单 */
    TAKE_PROFIT("TAKE_PROFIT"),
    /** 止盈市价单 */
    TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),
    /** 跟踪止损单 */
    TRAILING_STOP_MARKET("TRAILING_STOP_MARKET");

    override fun toString(): String {
        return code
    }
}