package pl.simpbot.binance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import pl.simpbot.binance.dtos.AssetWallet;
import pl.simpbot.config.PrivateConfig;
import pl.simpbot.enums.*;
import pl.simpbot.trader.Trade;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Mapper {
    private final ObjectMapper objectMapper;
    private final Gson gson;

    Mapper() {
        objectMapper = new ObjectMapper();
        this.gson = new Gson();
    }

    public Map<Coin, BigDecimal> userAssetsResponseToMap(String result) {
        Map<Coin, BigDecimal> wallet = new HashMap<>();
        AssetWallet[] assets;
        try {
            assets = objectMapper.readValue(result, AssetWallet[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List.of(assets).forEach(asset -> {
            wallet.put(Coin.valueOf(asset.getAsset()), new BigDecimal(asset.getFree()));

        });
        return wallet;
    }

    public BarSeries klineResponseToBarSeries(String response) {
        BarSeries series = new BaseBarSeries();
        series.setMaximumBarCount(PrivateConfig.MAX_BARS_IN_LIVE_SERIES);
        String adata = response.substring(1, response.length() - 2);
        adata = adata.replaceAll("\"", "").replaceFirst("\\[", "");
        String[] dataarray = adata.split("],\\[");
        Arrays.asList(dataarray).forEach(array -> {
            String[] kline = array.split(",");

            series.addBar(
                    ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(kline[6])), ZoneId.systemDefault()),
                    kline[1],
                    kline[2],
                    kline[3],
                    kline[4],
                    kline[5],
                    kline[7]);
        });
        return series;
    }

    public Map<Pair, String> exchangeInfoResponse(String json) {
        Map<Pair, String> stepSize = new HashMap<>();
        JsonObject response = gson.fromJson(json, JsonObject.class);
        JsonArray symbols = response.get("symbols").getAsJsonArray();

        symbols.forEach(symbol -> stepSize.put(
                findPairSymbol(symbol.getAsJsonObject().get("symbol").getAsString()),
                symbol.getAsJsonObject().get("filters").getAsJsonArray().get(1).getAsJsonObject().get("stepSize").getAsString()
        ));
        return stepSize;
    }

    private Pair findPairSymbol(String s) {
        return Arrays.stream(Pair.values()).filter(pair -> pair.getName().equals(s)).findFirst().get();
    }

    Trade newOrderResponseToTrade(String json) {
        JsonObject response = gson.fromJson(json, JsonObject.class);
        return new Trade(
                response.get("orderId").getAsLong(),
                findPairSymbol(response.get("symbol").getAsString()),
                response.get("clientOrderId").getAsString(),
                response.get("transactTime").getAsLong(),
                Double.valueOf(response.get("price").getAsString()),
                Double.valueOf(response.get("executedQty").getAsString()),
                OrderStatus.valueOf(response.get("status").getAsString()),
                OrderType.valueOf(response.get("type").getAsString()),
                TradeSide.valueOf(response.get("side").getAsString())
        );

    }
}
