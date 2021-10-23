package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Application {
    public static void main(String[] args) throws Exception {
        final Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();
        server.start();

        System.out.println("Server started...");

        server.awaitTermination();
    }
}
