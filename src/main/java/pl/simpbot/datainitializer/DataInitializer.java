package pl.simpbot.datainitializer;

import org.springframework.stereotype.Component;
import pl.simpbot.binance.BinanceFacade;
import pl.simpbot.config.StrategiesConfig;
import pl.simpbot.enums.Pair;
import pl.simpbot.strategy.rsi.RsiRulesDTO;
import pl.simpbot.trader.StrategyCarrier;
import pl.simpbot.trader.TraderFacade;
import pl.simpbot.wallet.WalletFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DataInitializer {
    private final BinanceFacade binanceFacade;
    private final WalletFacade walletFacade;
    private final TraderFacade traderFacade;
    private final StrategiesConfig strategiesConfig;

    public DataInitializer(BinanceFacade binanceFacade, WalletFacade walletFacade, TraderFacade traderFacade) {
        this.binanceFacade = binanceFacade;
        this.walletFacade = walletFacade;
        this.traderFacade = traderFacade;
        strategiesConfig = new StrategiesConfig();
    }

    public void initialize() {
        initializeStrategies();
        initializeExchangeInfo();
        initializeWallet();
    }

    void initializeWallet() {
        walletFacade.initWallet(binanceFacade.getInitAssets());
    }

    void initializeStrategies() {
        List<StrategyCarrier> strategies = new ArrayList<>();
        List<RsiRulesDTO> rsiRules = strategiesConfig.getRsiRules();

        rsiRules.forEach(rsiRulesDTO -> {
            strategies.add(new StrategyCarrier(rsiRulesDTO, binanceFacade.getInitBarSeries(rsiRulesDTO.getPair())));
        });
        traderFacade.setStrategies(strategies);
    }

    void initializeExchangeInfo() {
        Set<Pair> pairs = traderFacade.getActivePairs();
        Map<Pair, String> stepSize = binanceFacade.getExchangeInfo(pairs);
        traderFacade.setStepSize(stepSize);
    }
}
