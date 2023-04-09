package pl.simpbot.binance.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetWallet {
     String locked;
     String freeze;
     String withdrawing;
     String ipoable;
     String btcValuation;
     String free;
     String asset;

    public String getAsset() {
        return asset;
    }

    public String getFree() {
        return free;
    }
}
