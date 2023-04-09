package pl.simpbot.enums;

import static pl.simpbot.enums.Coin.*;

public enum Pair {
    BTC_USDT("BTCUSDT", BTC, USDT),
    ETH_USDT("ETHUSDT", ETH, USDT),
    AION_USDT("AIONUSDT", AION, USDT),
    BNB_USDT("BNBUSDT", BNB, USDT),
//    PSG_USDT("PSGUSDT"),
//    IOST_USDT("IOSTUSDT"),
//    NANO_USDT("NANOUSDT"),
//    FTM_USDT("FTMUSDT"),
//    DOGE_USDT("DOGEUSDT"),
//    ATOM_USDT("ATOMUSDT"),
//    ALGO_USDT("ALGOUSDT"),
//    SRM_USDT("SRMUSDT"),
//    XRP_USDT("XRPUSDT"),
//    SHIB_USDT("SHIBUSDT"),
//    ADA_USDT("ADAUSDT"),
//    MATIC_USDT("MATICUSDT"),
//    ICP_USDT("ICPUSDT"),
//    DOT_USDT("DOTUSDT"),
//    FIL_USDT("FILUSDT"),
//    VET_USDT("VETUSDT"),
//    LINK_USDT("LINKUSDT"),
//    SOL_USDT("SOLUSDT"),
//    KSM_USDT("KSMUSDT"),
//    THETA_USDT("THETAUSDT"),
//    EOS_USDT("EOSUSDT"),
//    TRX_USDT("TRXUSDT"),
//    BCH_USDT("BCHUSDT"),
//    AAVE_USDT("AAVEUSDT"),
//    CHZ_USDT("CHZUSDT"),
//    RUNE_USDT("RUNEUSDT"),
//    TFUEL_USDT("TFUELUSDT"),
//    BAKE_USDT("BAKEUSDT"),
//    NEO_USDT("NEOUSDT"),
//    SXP_USDT("SXPUSDT"),
//    UNI_USDT("UNIUSDT"),
//    LUNA_USDT("LUNAUSDT"),
//    CAKE_USDT("CAKEUSDT"),
//    BTT_USDT("BTTUSDT"),
//    XLM_USDT("XLMUSDT"),
//    SUSHI_USDT("SUSHIUSDT"),
//    COMP_USDT("COMPUSDT"),
//    ZEC_USDT("ZECUSDT"),
//    WIN_USDT("WINUSDT"),
//    ZIL_USDT("ZILUSDT"),
//    STORJ_USDT("STORJUSDT"),
//    EGLD_USDT("EGLDUSDT"),
//    YFI_USDT("YFIUSDT"),
//    PERP_USDT("PERPUSDT"),
//    CRV_USDT("CRVUSDT"),
//    XMR_USDT("XMRUSDT"),
//    DOCK_USDT("DOCKUSDT"),
//    ONT_USDT("ONTUSDT"),
//    GRT_USDT("GRTUSDT"),
//    DASH_USDT("DASHUSDT"),
//    DENT_USDT("DENTUSDT"),
//    QTUM_USDT("QTUMUSDT"),
//    INCH1_USDT("1INCHUSDT"),
//    OMG_USDT("OMGUSDT"),
//    ENJ_USDT("ENJUSDT"),
//    HOT_USDT("HOTUSDT"),
//    ALPHA_USDT("ALPHAUSDT"),
//    WAVES_USDT("WAVESUSDT"),
//    DATA_USDT("DATAUSDT"),
//    MBL_USDT("MBLUSDT"),
//    ATA_USDT("ATAUSDT"),
//    KAVA_USDT("KAVAUSDT"),
//    HBAR_USDT("HBARUSDT"),
//    CELO_USDT("CELOUSDT"),
//    GTC_USDT("GTCUSDT"),
//    IOTX_USDT("IOTXUSDT"),
//    AVAX_USDT("AVAXUSDT"),
//    NEAR_USDT("NEARUSDT"),
//    STMX_USDT("STMXUSDT"),
//    REEF_USDT("STMXUSDT"),
//    XTZ_USDT("XTZUSDT"),
//    XVS_USDT("XVSUSDT"),
//    MKR_USDT("MKRUSDT"),
//    LRC_USDT("LRCUSDT"),
//    IOTA_USDT("IOTAUSDT"),
//    ZEN_USDT("ZENUSDT"),
//    XEM_USDT("XEMUSDT"),
//    TLM_USDT("TLMUSDT"),
//    SKL_USDT("SKLUSDT"),
//    WRX_USDT("WRXUSDT"),
//    ALICE_USDT("ALICEUSDT"),
//    OGN_USDT("OGNUSDT"),
//    FTT_USDT("FTTUSDT"),
//    LSK_USDT("LSKUSDT"),
//    ONE_USDT("ONEUSDT"),
//    PERL_USDT("PERLUSDT"),
//    BAND_USDT("BANDUSDT"),
//    SC_USDT("SCUSDT"),
//    SNX_USDT("SNXUSDT"),
//    ANKR_USDT("ANKRUSDT"),
//    LINA_USDT("LINAUST"),
//    FET_USDT("FETUSDT"),
//    CTSI_USDT("CTSIUSDT"),
//    MDX_USDT("MDXUSDT")
    ;


    Pair(String name, Coin base, Coin quote) {
        this.name = name;
        this.base = base;
        this.quote = quote;
    }

    private final String name;
    private final Coin base;
    private final Coin quote;

    public String getName() {
        return name;
    }

    public Coin getQuote() {
        return quote;
    }

    public Coin getBase() {
        return base;
    }

    public static Pair findByName(String name){
        for (Pair pair : values()) {
            if (pair.getName().equals(name)) {
                return pair;
            }
        }
        return null;
    }
}
