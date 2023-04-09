package pl.simpbot.strategy.rsi;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;
import org.ta4j.core.rules.StopGainRule;
import org.ta4j.core.rules.StopLossRule;

public class RsiStrategy {

    public static Strategy buildStrategy(BarSeries series, RsiRulesDTO rules) {
        ClosePriceIndicator priceIndicator = new ClosePriceIndicator(series);
        RSIIndicator rsiIndicator = new RSIIndicator(priceIndicator, rules.getBarCount());

        Rule takeProfit = new StopGainRule(priceIndicator, rules.getGainPercent());
        Rule stopLoss = new StopLossRule(priceIndicator, rules.getLossPercent());
        Rule entry = new CrossedDownIndicatorRule(rsiIndicator, rules.getThresholdEnter());
        Rule exit = new CrossedUpIndicatorRule(rsiIndicator, rules.getThresholdExit()).or(takeProfit).or(stopLoss);
        String strategyName = createName(rules);
        return new BaseStrategy(strategyName, entry, exit);
    }

    private static String createName(RsiRulesDTO rules) {
        return new StringBuilder()
                .append("RsiStrategy: enter/exit ")
                .append(rules.getThresholdEnter())
                .append("/")
                .append(rules.getThresholdExit())
                .append(" bar count: ")
                .append(rules.getBarCount())
                .append(" gain/loss: ")
                .append(rules.getGainPercent())
                .append("/")
                .append(rules.getLossPercent())
                .toString();
    }
}
