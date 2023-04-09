package pl.simpbot;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import pl.simpbot.stream.StreamsProvider;

@Component
public class PreDestroyConfiguration {
    private final StreamsProvider streamsProvider;

    public PreDestroyConfiguration(StreamsProvider streamsProvider) {
        this.streamsProvider = streamsProvider;
    }

    @PreDestroy
    public void shutDown(){
        streamsProvider.closeStreams();
    }
}
