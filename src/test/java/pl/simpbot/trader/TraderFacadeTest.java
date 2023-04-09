package pl.simpbot.trader;

import org.junit.jupiter.api.Test;
import org.ta4j.core.num.DecimalNum;
import pl.simpbot.binance.BinanceFacade;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.*;
import pl.simpbot.wallet.WalletFacade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TraderFacadeTest {
    InMemoryPositionDatabaseImpl positionDatabase = new InMemoryPositionDatabaseImpl();

    @Test
    public void should_save_trade_to_db(){
        //given
        WalletFacade walletFacade = mock(WalletFacade.class);
        BinanceFacade binanceFacade = mock(BinanceFacade.class);
        OrderCreator orderCreator = mock(OrderCreator.class);
        Trader trader = new Trader(orderCreator, positionDatabase);
        StrategyCarrier strategyCarrier = mock(StrategyCarrier.class);
        trader.setStrategies(new ArrayList<>(List.of(strategyCarrier)));
        TraderFacade traderFacade = new TraderFacade(trader);

        KlineUpdateDto kline = new KlineUpdateDto(1680953832958L, Pair.BNB_USDT,1680953760000L, 1680953939999L, DecimalNum.valueOf(Double.valueOf("312.00000000")),DecimalNum.valueOf(Double.valueOf("312.00000000")),DecimalNum.valueOf(Double.valueOf("312.00000000")),DecimalNum.valueOf(Double.valueOf("312.00000000")),DecimalNum.valueOf(Double.valueOf("9015.00850000")), DecimalNum.valueOf(Double.valueOf("56.61600000")));
        Trade openingTrade = new Trade(1L, Pair.BTC_USDT,"123",123456L, 123.1,123.1, OrderStatus.FILLED, OrderType.MARKET, TradeSide.BUY);
        openingTrade.setStrategyName("str");
        Position position = new Position(openingTrade);

        when(strategyCarrier.getPair()).thenReturn(Pair.BNB_USDT);
        when(strategyCarrier.shouldTrade()).thenReturn(TradeAction.ENTER);
        when(walletFacade.getAssets(Coin.USDT)).thenReturn(BigDecimal.valueOf(11));
        when(orderCreator.buy(any())).thenReturn(position);
        //when
        traderFacade.updateKline(kline);
        Optional<Position> first = positionDatabase.findFirst();
        //then
        assertThat(first).isNotEmpty();
    }

}