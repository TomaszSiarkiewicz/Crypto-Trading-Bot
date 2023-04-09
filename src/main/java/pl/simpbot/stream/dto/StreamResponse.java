package pl.simpbot.stream.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamResponse {
    public String stream;
    public KlineData data;
}
