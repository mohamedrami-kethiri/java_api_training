package fr.lernejo.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MyServer {
    public void begin() throws IOException {
        int port = 9876;
        HttpServer server = HttpServer.create(new InetSocketAdress("localhost", port),O);
        server.setExecutor(Excutors.newFoxedThreadPool(1));
        server.createContext("/ping", new HttpHandlerHomeMader());
    }
}
