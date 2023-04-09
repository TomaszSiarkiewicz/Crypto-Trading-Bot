package pl.simpbot.binance;

import pl.simpbot.enums.Pair;
import pl.simpbot.enums.TradeSide;

import java.util.LinkedHashMap;

import static pl.simpbot.enums.OrderType.MARKET;

class NewOrderParamCreator {
    public static LinkedHashMap<String, Object> prepareMarketOrder(String quantity, String quoteOrderQuantity, Pair symbol, TradeSide side, String clientOrderIdOptional, String strategyIdOptional) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", MARKET.toString());
        parameters.put("newOrderRespType", "RESULT");

        if (quantity != null) {
            parameters.put("quantity", quantity);
        } else {
            parameters.put("quoteOrderQty", quoteOrderQuantity);
        }
        parameters.put("symbol", symbol.getName());
        parameters.put("side", side.name());
        if (clientOrderIdOptional != null) parameters.put("newClientOrderId", clientOrderIdOptional);
        if (strategyIdOptional != null) parameters.put("strategyId", strategyIdOptional);

        return parameters;
    }
}
