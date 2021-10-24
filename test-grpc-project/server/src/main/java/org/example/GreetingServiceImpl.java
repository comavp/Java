package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(final GreetingServiceOuterClass.HelloRequest request,
                         final StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        System.out.println(request);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse
                    .newBuilder()
                    .setGreeting("Hello, from gRPC server, " + request.getName())
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
