package pl.simpbot.binance.dtos;

import org.ta4j.core.num.Num;
import pl.simpbot.enums.Pair;

public record KlineUpdateDto(
        Long eventTime,
        Pair symbol,
        Long startTime,
        Long closeTime,
        Num open,
        Num close,
        Num high,
        Num low,
        Num volume,
        Num amount
) {
    @Override
    public String toString() {
        return "KlineUpdateDto{" +
                "eventTime=" + eventTime +
                ", symbol=" + symbol +
                ", startTime=" + startTime +
                ", closeTime=" + closeTime +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", amount=" + amount +
                '}';
    }
}
