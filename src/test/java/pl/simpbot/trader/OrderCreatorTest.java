package pl.simpbot.trader;

import com.binance.connector.client.exceptions.BinanceClientException;
import org.junit.jupiter.api.Test;
import pl.simpbot.binance.BinanceFacade;
import pl.simpbot.binance.BinanceService;
import pl.simpbot.enums.*;
import pl.simpbot.wallet.WalletFacade;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderCreatorTest {

    @Test
    public void buyOrderTest() {
        //given
        BinanceService binanceService = new BinanceService();
        BinanceFacade binanceFacade = new BinanceFacade(binanceService);
        WalletFacade walletFacade = mock(WalletFacade.class);
        OrderCreator orderCreator = new OrderCreator(walletFacade, binanceFacade);
        StrategyCarrier strategyCarrier = mock(StrategyCarrier.class);

        Map<Pair, String> step = new HashMap<>();
        step.put(Pair.BNB_USDT, "0.00100000");
        orderCreator.setStepSize(step);

        Map<Coin, BigDecimal> assets = new HashMap<>();
        assets.put(Coin.BNB, BigDecimal.valueOf(30000));
        when(walletFacade.getAssets(Coin.USDT)).thenReturn(BigDecimal.valueOf(30000));
        when(strategyCarrier.shouldTrade()).thenReturn(TradeAction.ENTER);
        when(strategyCarrier.getPair()).thenReturn(Pair.BNB_USDT);

        //when
        //then
        assertThrows(BinanceClientException.class, () -> orderCreator.buy(strategyCarrier));
    }

    @Test
    public void sellOrderTest() {
        //given
        BinanceService binanceService = new BinanceService();
        BinanceFacade binanceFacade = new BinanceFacade(binanceService);
        WalletFacade walletFacade = mock(WalletFacade.class);
        OrderCreator orderCreator = new OrderCreator(walletFacade, binanceFacade);
        StrategyCarrier strategyCarrier = mock(StrategyCarrier.class);

        Map<Pair, String> step = new HashMap<>();
        step.put(Pair.BNB_USDT, "0.001");
        orderCreator.setStepSize(step);


        when(walletFacade.getAssets(Coin.USDT)).thenReturn(BigDecimal.valueOf(3000));
        when(walletFacade.getAssets(Coin.BNB)).thenReturn(BigDecimal.valueOf(3000));
        when(strategyCarrier.shouldTrade()).thenReturn(TradeAction.ENTER);
        when(strategyCarrier.getPair()).thenReturn(Pair.BNB_USDT);

        Trade openTrade = new Trade(909L, Pair.BNB_USDT, "123", ZonedDateTime.now().toInstant().toEpochMilli(), 12.1, 1.1, OrderStatus.NEW, OrderType.MARKET, TradeSide.BUY);
        Position opened = new Position(openTrade);


        //when
        //then
        assertThrows(BinanceClientException.class, () -> orderCreator.sell(opened));
    }

    private void assertThat() {
    }

}