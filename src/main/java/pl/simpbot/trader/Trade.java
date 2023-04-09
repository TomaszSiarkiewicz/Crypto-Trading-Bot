package pl.simpbot.trader;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.simpbot.enums.OrderStatus;
import pl.simpbot.enums.OrderType;
import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeSide;

@Document
public class Trade {
    @Id
    private Long orderId;
    private Pair symbol;
    private String clientOrderId;
    private Long transactionTime;
    private Double price;
    private Double executedQty;
    private OrderStatus status;
    private OrderType type;
    private TradeSide side;
    private String strategyName;

    public Trade(Long orderId, Pair symbol, String clientOrderId, Long transactionTime, Double price, Double executedQty, OrderStatus status, OrderType type, TradeSide side) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.clientOrderId = clientOrderId;
        this.transactionTime = transactionTime;
        this.price = price;
        this.executedQty = executedQty;
        this.status = status;
        this.type = type;
        this.side = side;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Pair getSymbol() {
        return symbol;
    }

    public String getStrategyName() {
        return strategyName;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "symbol=" + symbol +
                ", price=" + price +
                ", status=" + status +
                ", type=" + type +
                ", side=" + side +
                ", strategyName='" + strategyName + '\'' +
                '}';
    }
}
