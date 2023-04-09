package pl.simpbot.trader;

import com.binance.connector.client.exceptions.BinanceClientException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.simpbot.binance.BinanceFacade;
import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeSide;
import pl.simpbot.wallet.WalletFacade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static pl.simpbot.enums.TradeSide.BUY;

@Log4j2
@Component
class OrderCreator {
    private final WalletFacade walletFacade;
    private final BinanceFacade binanceFacade;
    private Map<Pair, String> stepSize;
    private final NumberFormatter numberFormatter;

    OrderCreator(WalletFacade walletFacade, BinanceFacade binanceFacade) {
        this.walletFacade = walletFacade;
        this.binanceFacade = binanceFacade;
        numberFormatter = new NumberFormatter();
        stepSize = new HashMap<>();
    }


    public Position buy(StrategyCarrier strategyCarrier) {
        walletFacade.initWallet(binanceFacade.getInitAssets());
        BigDecimal availableFounds = walletFacade.getAssets(strategyCarrier.getPair().getQuote());
        try {
            Trade openTrade = binanceFacade.makeNewOrder(null, calculateBuyQuantity(stepSize.get(strategyCarrier.getPair()), availableFounds), strategyCarrier.getPair(), BUY, null, null);
            openTrade.setStrategyName(strategyCarrier.getName());
            return new Position(openTrade);
        } catch (BinanceClientException exception) {
            log.error("Error creating order", exception);
            throw exception;
        }
    }

    public Position sell(Position opened) {
        walletFacade.initWallet(binanceFacade.getInitAssets());
        String quantity = calculateSellQuantity(stepSize, opened.getPair());
        Trade closingTrade = binanceFacade.makeNewOrder(quantity, null, opened.getPair(), TradeSide.SELL, null, null);

        return new Position(opened, closingTrade);
    }

    public void setStepSize(Map<Pair, String> step) {
        stepSize = step;
    }

    String calculateBuyQuantity(String stepSize, BigDecimal availableFounds) {
        return formatNum(availableFounds, stepSize);
    }

    private String formatNum(BigDecimal founds, String stepSize) {
        Double size = Double.valueOf(stepSize);
        Double value = founds.doubleValue();
        if (size > 1) {
            return numberFormatter.formatGraterThan1(stepSize, value);
        } else if (size == 1) {
            return numberFormatter.formatEqualTo1(founds.longValue());
        } else {
            return numberFormatter.formatDecimals(stepSize, value);
        }
    }

    private String calculateSellQuantity(Map<Pair, String> stepSize, Pair pair) {

        return formatNum(walletFacade.getAssets(pair.getBase()), stepSize.get(pair));
    }
}
