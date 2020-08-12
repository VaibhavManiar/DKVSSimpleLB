package org.dkvs.client;

import org.dkvs.Pair;
import org.dkvs.Request;
import org.dkvs.Response;
import org.dkvs.client.parser.MessageParser;
import org.dkvs.client.tcp.TcpClient;
import org.dkvs.config.NodeConfig;
import org.dkvs.util.json.JsonUtil;


public class TcpClientFactory {
    public static TcpClient<Request<Pair>, Response<Pair>> create(NodeConfig nodeConfig) {
        return new TcpClient<>(getRequestMessageParser(), getResponseMessageParser(), nodeConfig.getIp(), nodeConfig.getPort());
    }

    private static MessageParser<Request<Pair>> getRequestMessageParser() {
        return new MessageParser<Request<Pair>>() {
            @Override
            public Request<Pair> deSerialize(String s) {
                return JsonUtil.toObject(s, Request.class);
            }

            @Override
            public String serialize(Request<Pair> s) {
                return JsonUtil.toJson(s);
            }

            @Override
            public Class<Request<Pair>> getType() {
                return null;
            }
        };
    }

    private static MessageParser<Response<Pair>> getResponseMessageParser() {
        return new MessageParser<Response<Pair>>() {
            @Override
            public Response<Pair> deSerialize(String s) {
                return JsonUtil.toObject(s, Response.class);
            }

            @Override
            public String serialize(Response<Pair> s) {
                return JsonUtil.toJson(s);
            }

            @Override
            public Class<Response<Pair>> getType() {
                return null;
            }
        };
    }
}