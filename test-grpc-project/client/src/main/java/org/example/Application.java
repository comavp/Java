package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;

import java.util.Iterator;

public class Application {
    public static void main(String[] args) throws Exception {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        final GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        final GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Java client")
                .build();

        final Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);
        while (response.hasNext()) {
            System.out.println(response.next());
        }

        channel.shutdownNow();
    }
}
