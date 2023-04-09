package pl.simpbot.stream.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * https://binance-docs.github.io/apidocs/spot/en/#trade-streams
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KlineData {
    public String e;
    public Long E;
    public String s;
    public KlineStreamDataDto k;

}
