package pl.simpbot.trader;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.Coin;
import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeAction;

import java.util.*;

@Log4j2
@Component
class Trader {
    private final OrderCreator orderCreator;
    private final PositionRepository positionRepository;
    private List<StrategyCarrier> strategies;
    private List<Position> openPositions;
    private List<Position> closedPositions;

    public Trader(OrderCreator orderCreator, PositionRepository positionRepository) {
        this.orderCreator = orderCreator;
        this.positionRepository = positionRepository;
        strategies = new ArrayList<>();
        openPositions = new ArrayList<>();
        closedPositions = new ArrayList<>();
    }


    List<Coin> getActiveCoins() {
        Set<Coin> coins = new HashSet<>();
        strategies.forEach(strategyCarrier -> {
            Pair pair = strategyCarrier.getPair();
            coins.add(pair.getQuote());
            coins.add(pair.getBase());
        });
        return coins.stream().toList();
    }

    void setStrategies(List<StrategyCarrier> strategies) {
        this.strategies = strategies;
    }

    Set<Pair> getActivePairs() {
        Set<Pair> pairs = new HashSet<>();
        strategies.forEach(strategyCarrier -> pairs.add(strategyCarrier.getPair()));
        return pairs;
    }

    public void updatePrice(KlineUpdateDto kline) {
        log.info("new prices: " + kline);
        strategies.forEach(strategyCarrier -> {
            if (strategyCarrier.getPair().equals(kline.symbol())) {
                strategyCarrier.updatePrice(kline);
                try{
                    Optional<Position> orderResult = makeOrder(strategyCarrier, openPositions);
                    if (orderResult.isPresent()){
                        positionRepository.save(orderResult.get());
                    }
                }catch (RuntimeException e){
                    log.error(e.getMessage());
                }
            }
        });
    }

    Optional<Position> makeOrder(StrategyCarrier strategyCarrier, List<Position> openPositions) {
        TradeAction action = strategyCarrier.shouldTrade();
        Optional<Position> position = isPositionOpen(strategyCarrier, openPositions);

        if (action.equals(TradeAction.EXIT)) {
            if (position.isPresent()) {
                return Optional.of(closePosition(position.get()));
            }
        } else if (action.equals(TradeAction.ENTER)){
            return Optional.of(openPosition(strategyCarrier));
        }
        return Optional.empty();
    }

    private Position openPosition(StrategyCarrier strategyCarrier) {
        Position buy = orderCreator.buy(strategyCarrier);
        log.info(buy);
        openPositions.add(buy);
        return buy;
    }

    private Position closePosition(Position openPosition) {
        Position closed = orderCreator.sell(openPosition);
        log.info(closed);
        closedPositions.add(closed);
        openPositions.remove(openPosition);
        return closed;
    }

    private Optional<Position> isPositionOpen(StrategyCarrier strategyCarrier, List<Position> openPositions) {
        return openPositions.stream().filter(position -> position.getStrategyName().equals(strategyCarrier.getName()) && position.getPair().equals(strategyCarrier.getPair())).findFirst();
    }

    public void setStepSize(Map<Pair, String> stepSize) {
        orderCreator.setStepSize(stepSize);
    }
}
