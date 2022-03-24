package com.binance.connector.client.enums;

/**
 * 合约类型:
 *
 PERPETUAL 永续合约
 CURRENT_MONTH 当月交割合约
 NEXT_MONTH 次月交割合约
 CURRENT_QUARTER 当季交割合约
 NEXT_QUARTER 次季交割合约
 */
public enum ContractType {
    PERPETUAL("PERPETUAL "),
    CURRENT_MONTH("CURRENT_MONTH"),
    NEXT_MONTH("NEXT_MONTH "),
    CURRENT_QUARTER("CURRENT_QUARTER"),
    NEXT_QUARTER("NEXT_QUARTER");

    private final String code;

    ContractType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
