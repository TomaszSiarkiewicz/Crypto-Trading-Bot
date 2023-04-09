package pl.simpbot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pl.simpbot.stream.StreamsProvider;
import pl.simpbot.datainitializer.DataInitializer;

@Component
public class PostConstructConfiguration {
    private final StreamsProvider streamsProvider;
    private final DataInitializer dataInitializer;

    public PostConstructConfiguration(StreamsProvider streamsProvider, DataInitializer dataInitializer) {
        this.streamsProvider = streamsProvider;
        this.dataInitializer = dataInitializer;

    }

    @PostConstruct
    public void initialization() {
        dataInitializer.initialize();
        streamsProvider.provideCoinPriceStream();
    }

}
