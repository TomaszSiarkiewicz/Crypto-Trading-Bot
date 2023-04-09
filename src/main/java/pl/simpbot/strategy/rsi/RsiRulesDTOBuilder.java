package pl.simpbot.strategy.rsi;

import pl.simpbot.enums.Pair;

public class RsiRulesDTOBuilder {
    private int barCount;
    private int thresholdEnter;
    private int thresholdExit;
    private double gainPercent;
    private double lossPercent;
    private String naturalId;
    private Pair pair;

    public RsiRulesDTOBuilder setBarCount(int barCount) {
        this.barCount = barCount;
        return this;
    }

    public RsiRulesDTOBuilder setThresholdEnter(int thresholdEnter) {
        this.thresholdEnter = thresholdEnter;
        return this;
    }

    public RsiRulesDTOBuilder setThresholdExit(int thresholdExit) {
        this.thresholdExit = thresholdExit;
        return this;
    }

    public RsiRulesDTOBuilder setGainPercent(double gainPercent) {
        this.gainPercent = gainPercent;
        return this;
    }

    public RsiRulesDTOBuilder setLossPercent(double lossPercent) {
        this.lossPercent = lossPercent;
        return this;
    }
    public RsiRulesDTOBuilder setPair(Pair pair){
        this.pair = pair;
        return this;
    }

    public RsiRulesDTO build() {
        this.naturalId = barCount + "-" + thresholdEnter + "-" + thresholdExit + "-" + gainPercent + "-" + lossPercent;
        return new RsiRulesDTO(this);
    }

    public int getBarCount() {
        return barCount;
    }

    public int getThresholdEnter() {
        return thresholdEnter;
    }

    public int getThresholdExit() {
        return thresholdExit;
    }

    public double getGainPercent() {
        return gainPercent;
    }

    public double getLossPercent() {
        return lossPercent;
    }

    public String getNaturalId() {
        return naturalId;
    }

    public Pair getPair() {
        return pair;
    }
}

