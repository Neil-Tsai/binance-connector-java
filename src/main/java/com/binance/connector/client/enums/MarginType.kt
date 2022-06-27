package com.binance.connector.client.enums

enum class MarginType(val code: String) {
    /**
     * 保证金模式
     * ISOLATED(逐仓),
     * CROSSED(全仓)
     */
    ISOLATED("ISOLATED"),
    CROSSED("CROSSED");

    override fun toString(): String {
        return code
    }
}