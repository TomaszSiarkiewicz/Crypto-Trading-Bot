package pl.simpbot.config;

import pl.simpbot.enums.Pair;
import pl.simpbot.strategy.rsi.RsiRulesDTO;
import pl.simpbot.strategy.rsi.RsiRulesDTOBuilder;

import java.util.List;

public class StrategiesConfig {
    private final List<RsiRulesDTO> rsiRules;

    public StrategiesConfig() {
        rsiRules = List.of(
                new RsiRulesDTOBuilder()
                        .setBarCount(21)
                        .setThresholdEnter(25)
                        .setThresholdExit(50)
                        .setGainPercent(5)
                        .setLossPercent(5)
                        .setPair(Pair.BNB_USDT)
                        .build()
        );
    }

    public List<RsiRulesDTO> getRsiRules() {
        return rsiRules;
    }

}
