package com.binance.connector.client.enums

/**
 * 订单方向 (side)
 */
enum class OrderSide(val code: String) {
    /** 买入 */
    BUY("BUY"),
    /** 卖出 */
    SELL("SELL");

    override fun toString(): String {
        return code
    }
}