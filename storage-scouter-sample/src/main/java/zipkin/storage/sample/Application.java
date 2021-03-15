package zipkin.storage.sample;

import com.linecorp.armeria.spring.ArmeriaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import zipkin2.server.internal.ZipkinConfiguration;
import zipkin2.server.internal.ZipkinHttpCollector;
import zipkin2.server.internal.ZipkinHttpConfiguration;

@SpringBootApplication
@Import({
        ArmeriaAutoConfiguration.class,
        ZipkinConfiguration.class,
        ZipkinHttpConfiguration.class,
        ZipkinHttpCollector.class,
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
