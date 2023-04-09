package pl.simpbot.dataupdater;

import org.springframework.stereotype.Component;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.Pair;
import pl.simpbot.trader.TraderFacade;

import java.util.Set;

@Component
public class DataUpdater {
    private final TraderFacade traderFacade;

    public DataUpdater(TraderFacade traderFacade) {
        this.traderFacade = traderFacade;
    }

    public Set<Pair> activePairs() {
        return traderFacade.getActivePairs();
    }

    public void klineUpdate(KlineUpdateDto kline) {
        traderFacade.updateKline(kline);
    }
}
