package org.dkvs.config;

import org.dkvs.config.exception.ConfigLoaderException;
import org.dkvs.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TcpClientConfiguration implements ClientConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(TcpClientConfiguration.class);

    private final Properties properties;

    public TcpClientConfiguration(String profile) {
        this.properties = new ConfigLoader(configFile(profile)).load();
    }

    private String configFile(String profile) {
        String prefix = "src/main/resources/";
        String file = "";
        if (profile != null && !profile.trim().isEmpty()) {
            file = "server-" + profile + ".properties";
        } else {
            file = prefix + "server.properties";
        }
        logger.info("Client config file : " + file);
        return file;
    }

    @Override
    public int numberOfNodes() {
        String nodeCountStr = properties.getProperty(Constants.nodeCount);
        logger.info("Number of node count : " + nodeCountStr);
        if (nodeCountStr == null || nodeCountStr.trim().isEmpty()) {
            throw new ConfigLoaderException("Error while fetching node count from configuration.");
        }
        return Integer.parseInt(nodeCountStr);
    }

    @Override
    public List<NodeConfig> nodeConfigs() {
        String nodeConfig = this.properties.getProperty(Constants.config);
        logger.info("Node configuration : " + nodeConfig);
        if (nodeConfig == null || !nodeConfig.trim().isEmpty()) {
            throw new ConfigLoaderException("Error while loading node configuration. server config is null or empty.");
        }
        return Arrays.asList(JsonUtil.toObject(nodeConfig, NodeConfig[].class));
    }

    private interface Constants {
        String PORT = "port";
        String IP = "ip";
        String config = "server.config";
        String nodeCount = "server.node.count";
    }
}
