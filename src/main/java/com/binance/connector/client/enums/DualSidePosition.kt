package com.binance.connector.client.enums

enum class DualSidePosition(val code: String) {
    SINGLE("false"), //单向持仓模式
    DOUBLE("true"); //双向持仓模式

    override fun toString(): String {
        return code
    }
}