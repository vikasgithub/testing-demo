package example.springframework.boot.minimal;

import example.armeria.grpc.Hello;
import example.armeria.grpc.HelloService2Grpc;
import example.armeria.grpc.HelloServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;


@Component
public class HelloService2Impl extends HelloService2Grpc.HelloService2ImplBase {

    @Override
    public void hello(Hello.HelloRequest request, StreamObserver<Hello.HelloReply> responseObserver) {
        if (request.getName().isEmpty()) {
            responseObserver.onError(
                    Status.FAILED_PRECONDITION.withDescription("Name cannot be empty").asRuntimeException());
        } else {
            responseObserver.onNext(buildReply(toMessage(request.getName())));
            responseObserver.onCompleted();
        }
    }

    static String toMessage(String name) {
        return "Hello, " + name + '!';
    }

    private static Hello.HelloReply buildReply(Object message) {
        return Hello.HelloReply.newBuilder().setMessage(String.valueOf(message)).build();
    }
}
