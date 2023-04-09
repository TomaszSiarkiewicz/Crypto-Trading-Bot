package pl.simpbot.binance;

import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;
import pl.simpbot.enums.Coin;
import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeSide;
import pl.simpbot.trader.Trade;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
public class BinanceFacade {
    private final BinanceService binanceService;

    public BinanceFacade( BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    public Map<Coin, BigDecimal> getInitAssets(){
        return binanceService.getAssets();
    }

    public BarSeries getInitBarSeries(Pair pair) {
        return binanceService.getInitBarSeries(pair);
    }

    public Map<Pair, String> getExchangeInfo(Set<Pair> pairs) {
        return binanceService.getExchengeInfo(pairs);
    }

    public Trade makeNewOrder(String quantity, String quoteOrderQuantity, Pair pair, TradeSide tradeSide, String clientOrderIdOptional, String strategyIdOptional) {
        LinkedHashMap<String, Object> parameters = NewOrderParamCreator.prepareMarketOrder(quantity, quoteOrderQuantity, pair, tradeSide, clientOrderIdOptional, strategyIdOptional);
        return binanceService.newOrder(parameters);
    }
}
