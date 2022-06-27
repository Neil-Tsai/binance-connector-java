package com.binance.connector.client.enums

/**
 * 有效方式 (timeInForce
 */
enum class TimeInForce(val code: String) {
    GTC("GTC"), //- Good Till Cancel 成交为止
    IOC("IOC"), //- Immediate or Cancel 无法立即成交(吃单)的部分就撤销
    FOK("FOK"), //- Fill or Kill 无法全部立即成交就撤销
    GTX("GTX"); //- Good Till Crossing 无法成为挂单方就撤销

    override fun toString(): String {
        return code
    }
}