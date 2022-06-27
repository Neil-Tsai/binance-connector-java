package com.binance.connector.client.enums

/**
 * 持仓方向
 * 单向持仓模式下非必填，默认且仅可填BOTH;
 * 在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
 * 放空 是種 短期 (Short) 的炒作，
 * 做多 則相對是 長期 (Long) 的投資，
 */
enum class PositionSide(val code: String) {
    /** 单一持仓方向 */
    BOTH("BOTH"),
    /** 空头(双向持仓下) */
    SHORT("SHORT"),
    /** 多头(双向持仓下) */
    LONG("LONG");

    override fun toString(): String {
        return code
    }
}