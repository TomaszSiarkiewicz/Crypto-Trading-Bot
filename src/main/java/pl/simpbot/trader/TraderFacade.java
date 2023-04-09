package pl.simpbot.trader;

import org.springframework.stereotype.Component;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TraderFacade {
    private final Trader trader;

    public TraderFacade(Trader trader) {
        this.trader = trader;
    }

    public void setStrategies(List<StrategyCarrier> strategies) {
        trader.setStrategies(strategies);
    }

    public Set<Pair> getActivePairs() {
        return trader.getActivePairs();
    }

    public void updateKline(KlineUpdateDto kline) {
        trader.updatePrice(kline);
    }

    public void setStepSize(Map<Pair, String> stepSize) {
        trader.setStepSize(stepSize);
    }
}
