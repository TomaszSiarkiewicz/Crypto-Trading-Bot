package pl.simpbot.enums;

public enum Coin {
    USDT("USDT"),
    BTC("BTC"),
    ETH("ETH"),
    AION("AION"),
    BNB("BNB");

    Coin(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
