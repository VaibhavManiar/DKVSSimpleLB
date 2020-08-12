package org.dkvs.lb;

import org.dkvs.Pair;
import org.dkvs.Request;
import org.dkvs.Response;
import org.dkvs.client.TcpClientFactory;
import org.dkvs.client.tcp.TcpClient;
import org.dkvs.config.ClientConfiguration;
import org.dkvs.config.TcpClientConfiguration;

public class NodeClient {
    private final ClientConfiguration clientConfiguration = new TcpClientConfiguration("");
    private final int divisionVal;

    public NodeClient() {
        this.divisionVal = Integer.MAX_VALUE / clientConfiguration.numberOfNodes();
    }

    public Response<Pair> send(Request<Pair> request) {
        int hashcode = request.getData().getKey().hashCode();
        final Response<Pair>[] response = new Response[1];
        getTcpClient(nodeIndex(hashcode)).send(request, s -> response[0] = s);
        return response[0];
    }

    private int nodeIndex(int keyHashCode) {
        return (keyHashCode / divisionVal);
    }

    private TcpClient<Request<Pair>, Response<Pair>> getTcpClient(int nodeIndex) {
        return TcpClientFactory.create(clientConfiguration.nodeConfigs().get(nodeIndex));
    }
}
