package pl.simpbot.stream;

import org.junit.jupiter.api.Test;


class StreamMapperTest {
    @Test
    public void shouldMapResponseToKlineDTO() {
        //given
        String response = "{\"stream\":\"btcusdt@kline_3m\",\"data\":{\"e\":\"kline\",\"E\":1680967906369,\"s\":\"BTCUSDT\",\"k\":{\"t\":1680967800000,\"T\":1680967979999,\"s\":\"BTCUSDT\",\"i\":\"3m\",\"f\":3074478883,\"L\":3074479539,\"o\":\"28007.63000000\",\"c\":\"28007.52000000\",\"h\":\"28007.63000000\",\"l\":\"28007.51000000\",\"v\":\"12.38850000\",\"n\":657,\"x\":false,\"q\":\"346971.34909940\",\"V\":\"7.77417000\",\"Q\":\"217735.38169350\",\"B\":\"0\"}}}";
        StreamMapper mapper = new StreamMapper();
        //when
        //then
        mapper.klineStreamToKlineDto(response);

    }
}