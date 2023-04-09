package pl.simpbot.binance;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import pl.simpbot.enums.Coin;
import pl.simpbot.enums.Pair;

import java.math.BigDecimal;
import java.util.Map;


import static org.assertj.core.api.Assertions.*;

class BinanceServiceTest {
    BinanceService binanceService = new BinanceService();

    @Test
    public void shouldMapWalletResponse(){
        Map<Coin, BigDecimal> assets = binanceService.getAssets();

        assertThat(assets).isNotEmpty();
    }
    @Test
    public void shouldReturnBarSeries(){
        BarSeries series = binanceService.getInitBarSeries(Pair.BNB_USDT);
        assertThat(series.getBarCount()).isGreaterThan(0);
    }

}