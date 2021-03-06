package com.vijayrc.supportguy.web;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@Scope("singleton")
@Log4j
public class MyServer {
    private Integer port;
    private Server myServer;
    private MyContainer myContainer;

    @Autowired
    public MyServer(MyContainer myContainer, @Value("#{config['server.port']}") Integer port) {
        this.myContainer = myContainer;
        this.port = port;
    }

    public void start() throws Exception {
        myServer = new ContainerServer(myContainer);
        Connection connection = new SocketConnection(myServer);
        connection.connect(new InetSocketAddress(port));
        log.info("support-guy server started, ready to process request...");
    }

    public void stop() throws Exception {
        myServer.stop();
        log.info("support-guy server stopped");
    }
}
