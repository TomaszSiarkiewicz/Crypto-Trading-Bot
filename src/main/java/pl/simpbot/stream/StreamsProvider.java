package pl.simpbot.stream;

import com.binance.connector.client.impl.WebsocketClientImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import pl.simpbot.binance.dtos.KlineUpdateDto;
import pl.simpbot.dataupdater.DataUpdater;
import pl.simpbot.enums.Pair;

import java.util.ArrayList;
import java.util.Set;

import static pl.simpbot.config.PrivateConfig.KLINE_TIME_INTERVAL;

@Log4j2
@Component
public class StreamsProvider {
    private final DataUpdater dataUpdater;
    private final WebsocketClientImpl websocketClient;
    private final StreamMapper streamMapper;


    StreamsProvider(DataUpdater dataUpdater) {
        this.dataUpdater = dataUpdater;
        streamMapper = new StreamMapper();
        websocketClient = new WebsocketClientImpl();
    }

    public void provideCoinPriceStream() {
        log.info("attempting to connect...");
        Set<Pair> pairs = dataUpdater.activePairs();
        ArrayList<String> config = prepareKlineStreams(pairs);
        websocketClient.combineStreams(config, this::connectionEstablished, this::process, this::connectionClosed, this::connectionFailure);
    }

    private ArrayList<String> prepareKlineStreams(Set<Pair> pairs) {
        ArrayList<String> config = new ArrayList<>();
        pairs.forEach(coin -> {
            config.add(coin.getName().toLowerCase() + "@kline_" + KLINE_TIME_INTERVAL);
        });
        return config;
    }

    public void process(String data) {
        KlineUpdateDto kline = streamMapper.klineStreamToKlineDto(data);
        dataUpdater.klineUpdate(kline);
    }

    public void connectionEstablished(String data) {
    }

    private void connectionFailure(String data) {
        websocketClient.closeAllConnections();
    }

    private void connectionClosed(String data) {
    }

    public void closeStreams() {
        websocketClient.closeAllConnections();
    }

}
