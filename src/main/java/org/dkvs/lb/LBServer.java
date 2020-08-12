package org.dkvs.lb;

public class LBServer {

    public LBServer() {
        server();
    }

    private void server() {
        LBStarter.start();
    }

    public static void main(String[] args) {
        new LBServer();
    }
}
