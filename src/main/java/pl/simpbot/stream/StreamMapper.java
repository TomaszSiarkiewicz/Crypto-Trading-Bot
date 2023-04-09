package pl.simpbot.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ta4j.core.num.DecimalNum;
import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.enums.Pair;
import pl.simpbot.stream.dto.KlineData;
import pl.simpbot.stream.dto.StreamResponse;

class StreamMapper {
    private final ObjectMapper objectMapper;

    StreamMapper() {
        objectMapper = new ObjectMapper();
    }

    public KlineUpdateDto klineStreamToKlineDto(String data) {
        KlineData kline = new KlineData();
        try {
            kline = objectMapper.readValue(data, StreamResponse.class).data;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new KlineUpdateDto(
                kline.E,
                Pair.findByName(kline.s),
                kline.k.t,
                kline.k.T,
                DecimalNum.valueOf(kline.k.o),
                DecimalNum.valueOf(kline.k.c),
                DecimalNum.valueOf(kline.k.h),
                DecimalNum.valueOf(kline.k.l),
                DecimalNum.valueOf(kline.k.Q),
                DecimalNum.valueOf(kline.k.v)
        );
    }
}
