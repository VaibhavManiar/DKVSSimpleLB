package org.dkvs.lb;

import org.dkvs.tcp.server.TcpServerBootstrap;

public class LBStarter {
    public static void start() {
        TcpServerBootstrap.main(new String[0]);
    }
}
