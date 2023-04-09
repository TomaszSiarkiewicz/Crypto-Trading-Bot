package pl.simpbot.strategy.rsi;


import pl.simpbot.enums.Pair;

public class RsiRulesDTO {
    private String naturalId;
    private int barCount;
    private int thresholdEnter;
    private int thresholdExit;
    private double gainPercent;
    private double lossPercent;
    private Pair pair;

    public RsiRulesDTO(RsiRulesDTOBuilder builder) {
        this.barCount = builder.getBarCount();
        this.thresholdEnter = builder.getThresholdEnter();
        this.thresholdExit = builder.getThresholdExit();
        this.gainPercent = builder.getGainPercent();
        this.lossPercent = builder.getLossPercent();
        this.naturalId = builder.getNaturalId();
        this.pair = builder.getPair();

    }

    public RsiRulesDTO() {

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

    public Pair getPair() {
        return pair;
    }

    public String getNaturalId() {
        return naturalId;
    }

    @Override
    public String toString() {
        return "RsiRulesDTO{" +
                "naturalId='" + naturalId + '\'' +
                ", barCount=" + barCount +
                ", thresholdEnter=" + thresholdEnter +
                ", thresholdExit=" + thresholdExit +
                ", gainPercent=" + gainPercent +
                ", lossPercent=" + lossPercent +
                ", pair=" + pair +
                '}';
    }
}
