package org.dkvs.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeConfig {
    private final int port;
    private final String ip;

    @JsonCreator
    public NodeConfig(@JsonProperty("port") int port, @JsonProperty("ip") String ip) {
        this.port = port;
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}
