package example.springframework.boot.minimal;

import com.linecorp.armeria.client.grpc.GrpcClients;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.testing.junit5.server.ServerExtension;
import example.armeria.grpc.Hello;
import example.armeria.grpc.HelloServiceGrpc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloSpringBootTest {

    @RegisterExtension
    static final ServerExtension server = new ServerExtension() {
        @Override
        protected void configure(ServerBuilder sb) throws Exception {
            sb.service(GrpcService.builder()
                    // Add the service to the configuration
                    .addService(new HelloServiceImpl())
                    .build()).build();
        }
    };

    @Test
    public void testHello() {
        final HelloServiceGrpc.HelloServiceBlockingStub helloService =
                GrpcClients.newClient(uri(), HelloServiceGrpc.HelloServiceBlockingStub.class);
        assertThat(helloService.hello(Hello.HelloRequest.newBuilder().setName("Armeria").build()).getMessage())
                .isEqualTo("Hello, Armeria!");
    }

    private static String uri() {
        return server.httpUri(GrpcSerializationFormats.PROTO).toString();
    }
}
