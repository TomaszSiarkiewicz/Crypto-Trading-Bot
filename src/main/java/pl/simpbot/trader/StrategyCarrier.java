package pl.simpbot.trader;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeAction;
import pl.simpbot.strategy.rsi.RsiRulesDTO;
import pl.simpbot.strategy.rsi.RsiStrategy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class StrategyCarrier {
    private final Strategy strategy;
    private final Pair pair;
    private final BarSeries series;
    private final Mapper mapper;

    public StrategyCarrier(RsiRulesDTO rulesDTO, BarSeries series) {
        this.strategy = RsiStrategy.buildStrategy(series, rulesDTO);
        this.pair = rulesDTO.getPair();
        this.series = series;
        mapper = new Mapper();
    }

    public Pair getPair() {
        return pair;
    }

    public void updatePrice(KlineUpdateDto kline) {
        boolean shouldReplaceLastBar = shouldReplace(kline);
        updateLastBar(kline, shouldReplaceLastBar);
    }

    private void updateLastBar(KlineUpdateDto kline, boolean shouldReplaceLastBar) {
        series.addBar(mapper.klineToBar(kline), shouldReplaceLastBar);
    }

    private boolean shouldReplace(KlineUpdateDto kline) {
        ZonedDateTime lastBarEndTime = series.getLastBar().getEndTime();
        ZonedDateTime newBarEndTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(kline.closeTime()), ZoneId.systemDefault());

        return lastBarEndTime.isEqual(newBarEndTime);
    }

    TradeAction shouldTrade() {
        if (strategy.shouldEnter(series.getEndIndex())) {
            return TradeAction.ENTER;
        } else if (strategy.shouldExit(series.getEndIndex())) {
            return TradeAction.EXIT;
        } else {
            return TradeAction.WAIT;
        }
    }

    String getName() {
        return strategy.getName();
    }
}
