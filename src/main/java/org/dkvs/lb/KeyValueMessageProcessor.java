package org.dkvs.lb;

import org.dkvs.Pair;
import org.dkvs.Request;
import org.dkvs.Response;
import org.dkvs.tcp.message.processor.MessageProcessor;

public class KeyValueMessageProcessor implements MessageProcessor<Request<Pair>, Response<Pair>> {

    private final NodeClient nodeClient = new NodeClient();

    public KeyValueMessageProcessor() {

    }

    @Override
    public Response<Pair> process(Request<Pair> request) {
        return nodeClient.send(request);
    }
}
