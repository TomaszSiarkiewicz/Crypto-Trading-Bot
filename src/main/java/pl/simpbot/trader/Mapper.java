package pl.simpbot.trader;

import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.config.PrivateConfig;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class Mapper {
    public Bar klineToBar(KlineUpdateDto kline) {
        return new BaseBar(
                PrivateConfig.KLINE_DURATION,
                ZonedDateTime.ofInstant(Instant.ofEpochMilli(kline.closeTime()), ZoneId.systemDefault()),
                kline.open(),
                kline.high(),
                kline.low(),
                kline.close(),
                kline.volume(),
                kline.amount()
        );
    }
}
