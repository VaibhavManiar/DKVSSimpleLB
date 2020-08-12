package org.dkvs.lb.config;

import org.dkvs.Pair;
import org.dkvs.Request;
import org.dkvs.Response;
import org.dkvs.lb.KeyValueMessageProcessor;
import org.dkvs.tcp.config.Configuration;
import org.dkvs.tcp.message.parser.MessageParser;
import org.dkvs.tcp.message.processor.MessageProcessor;
import org.dkvs.tcp.server.ServerProperties;
import org.dkvs.util.json.JsonUtil;

public class LBMessageConfiguration implements Configuration<Request<Pair>, Response<Pair>> {

    private final ServerProperties serverProperties;

    public LBMessageConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public MessageProcessor<Request<Pair>, Response<Pair>> getMessageProcessor() {
        return new KeyValueMessageProcessor();
    }

    @Override
    public MessageParser<Request<Pair>> getRequestParser() {
        return getRequestMessageParser();
    }

    @Override
    public MessageParser<Response<Pair>> getResponseParser() {
        return getResponseMessageParser();
    }

    @Override
    public int port() {
        return Integer.parseInt(serverProperties.getPortIfConfigured().orElse("8666"));
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
