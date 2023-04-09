package pl.simpbot.wallet;

import org.springframework.stereotype.Component;
import pl.simpbot.enums.Coin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
class Wallet {
    Map<Coin, BigDecimal> assets;

    public Wallet() {
        assets = new HashMap<>();
    }

    public void updateAssets(Map<Coin, BigDecimal> initAssets) {
        assets.putAll(initAssets);
    }
}
