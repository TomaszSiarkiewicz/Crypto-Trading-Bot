package pl.simpbot.wallet;

import org.springframework.stereotype.Component;
import pl.simpbot.enums.Coin;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class WalletFacade {
    private final Wallet wallet;

    public WalletFacade(Wallet wallet) {
        this.wallet = wallet;
    }

    public void initWallet(Map<Coin, BigDecimal> initAssets) {

        wallet.updateAssets(initAssets);
    }

    public BigDecimal getAssets(Coin coin) {
        return wallet.assets.get(coin);
    }
}
