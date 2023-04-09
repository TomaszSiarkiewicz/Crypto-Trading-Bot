package pl.simpbot.binance;

import com.binance.connector.client.impl.SpotClientImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;
import pl.simpbot.config.PrivateConfig;
import pl.simpbot.enums.Coin;
import pl.simpbot.enums.Pair;
import pl.simpbot.trader.Trade;

import java.math.BigDecimal;
import java.util.*;

@Log4j2
@Component
public class BinanceService {
    private final SpotClientImpl spotClient;
    private final Mapper mapper;


    public BinanceService() {
        spotClient = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL);
        mapper = new Mapper();
    }

    Map<Coin, BigDecimal> getAssets() {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        String result = spotClient.createWallet().getUserAsset(parameters);
        return mapper.userAssetsResponseToMap(result);
    }

    BarSeries getInitBarSeries(Pair pair) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", pair.getName());
        parameters.put("interval", PrivateConfig.KLINE_TIME_INTERVAL);
        parameters.put("limit", 500);

        String response = spotClient.createMarket().klines(parameters);
        return mapper.klineResponseToBarSeries(response);
    }

     Map<Pair, String> getExchengeInfo(Set<Pair> pairs) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        List<String> pairsString = new ArrayList<>();
        pairs.forEach(pair -> pairsString.add(pair.getName()));
        parameters.put("symbols", pairsString);
        String response = spotClient.createMarket().exchangeInfo(parameters);
        return mapper.exchangeInfoResponse(response);
    }

     Trade newOrder(LinkedHashMap<String, Object> parameters) {
        String response = spotClient.createTrade().newOrder(parameters);
        return mapper.newOrderResponseToTrade(response);
    }
}
