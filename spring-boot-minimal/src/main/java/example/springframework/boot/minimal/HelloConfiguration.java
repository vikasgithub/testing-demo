package example.springframework.boot.minimal;

import com.linecorp.armeria.server.DecoratingHttpServiceFunction;
import com.linecorp.armeria.server.DecoratingRpcServiceFunction;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcService;
import io.grpc.BindableService;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;

import java.util.List;

/**
 * An example of a configuration which provides beans for customizing the server and client.
 */
@Configuration
public class HelloConfiguration {

    /**
     * A user can configure a {@link Server} by providing an {@link ArmeriaServerConfigurator} bean.
     */
    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator(List<BindableService> grpcServices) {
        // Customize the server using the given ServerBuilder. For example:
        return builder -> {
            configureServices(grpcServices, builder);

//            // Add DocService that enables you to send Thrift and gRPC requests from web browser.
//            builder.serviceUnder("/docs", new DocService());
//
//            // Log every message which the server receives and responds.
//            builder.decorator(LoggingService.newDecorator());
//
//            // Write access log after completing a request.
//            builder.accessLogWriter(AccessLogWriter.combined(), false);
//
//            // Add an Armeria annotated HTTP service.
//            builder.annotatedService(service);
//
//
//            // You can also bind asynchronous RPC services such as Thrift and gRPC:
//            // builder.service(THttpService.of(...));
//            // builder.service(GrpcService.builder()...build());
        };
    }

    private static void configureServices(List<BindableService> grpcServices, ServerBuilder builder) {
        builder.service(
                GrpcService.builder()
                        .addService(ProtoReflectionService.newInstance())
                        .addServices(grpcServices)
                        .build()
        );
        Logger logger = LoggerFactory.getLogger("com.example2.my.access.logs");
        builder.accessLogger(logger);
        builder.accessLogWriter(AccessLogWriter.combined(), true);
        builder.decorator(DecoratingRpcServiceFunction)
    }
}
