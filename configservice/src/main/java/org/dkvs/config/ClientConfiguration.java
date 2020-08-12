package org.dkvs.config;

import java.util.List;

public interface ClientConfiguration {
    int numberOfNodes();
    List<NodeConfig> nodeConfigs();
}
