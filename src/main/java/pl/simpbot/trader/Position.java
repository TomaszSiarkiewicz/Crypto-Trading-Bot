package pl.simpbot.trader;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.simpbot.enums.Pair;

import java.util.UUID;

@Document
public class Position {
    @Id
    private UUID uuid;
    private Pair pair;
    private String strategyName;
    private Trade openingTrade;
    private Trade closingTrade;

    public Position(Trade openTrade) {
        uuid = UUID.randomUUID();
        pair = openTrade.getSymbol();
        strategyName = openTrade.getStrategyName();
        openingTrade = openTrade;
    }

    public Position(Position position, Trade closingTrade) {
        this.uuid = position.uuid;
        this.pair = position.pair;
        this.strategyName = position.strategyName;
        this.openingTrade = position.openingTrade;
        this.closingTrade = closingTrade;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public Pair getPair() {
        return pair;
    }

    public void setClosingTrade(Trade closingTrade) {
        this.closingTrade = closingTrade;
    }

    @Override
    public String toString() {
        return "Position{" +
                "uuid=" + uuid +
                ", pair=" + pair +
                ", strategyName='" + strategyName + '\'' +
                ", openingTrade=" + openingTrade +
                ", closingTrade=" + closingTrade +
                '}';
    }
}
