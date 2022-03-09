package com.binance.connector.client.enums;

/**
 * 合约类型:
 *
 * perpetual 永续合约
 * current_quarter 当季交割合约
 * next_quarter 次季交割合约
 */
public enum ContractType {
    PERPETUAL("perpetual "),
    CURRENT_QUARTER("current_quarter"),
    NEXT_QUARTER("next_quarter ");

    private final String code;

    ContractType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
