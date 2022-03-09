package com.binance.connector.client.enums;

/**
 * m -> 分钟;
 * h -> 小时;
 * d -> 天;
 * w -> 周;
 * M -> 月
 */
public enum TimeInterval {
    ONE_MINUTE("1m"),
    THREE_MINUTES("3m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    HALF_HOURLY("30m"),
    HOURLY("1h"),
    TWO_HOURLY("2h"),
    FOUR_HOURLY("4h"),
    SIX_HOURLY("6h"),
    EIGHT_HOURLY("8h"),
    TWELVE_HOURLY("12h"),
    DAILY("1d"),
    THREE_DAILY("3d"),
    WEEKLY("1w"),
    MONTHLY("1M");

    private final String code;

    TimeInterval(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
