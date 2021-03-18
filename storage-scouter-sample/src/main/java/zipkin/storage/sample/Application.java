package zipkin.storage.sample;

import com.linecorp.armeria.spring.ArmeriaAutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import zipkin2.Span;
import zipkin2.reporter.Reporter;
import zipkin2.server.internal.ZipkinConfiguration;
import zipkin2.server.internal.ZipkinHttpCollector;
import zipkin2.server.internal.ZipkinHttpConfiguration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

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

    @Bean
    @Order
    BeanPostProcessor reporterBeanPostProcessor() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return new BeanPostProcessor() {
            @Override
            @SuppressWarnings("unchecked")
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if ("zipkinReporter".equals(beanName) && bean instanceof Reporter) {
                    return (Reporter<Span>) span -> Optional.ofNullable(span)
                            .map(s -> s.toBuilder().putTag("hostname", hostname).build())
                            .ifPresent(((Reporter<Span>) bean)::report);
                } else {
                    return bean;
                }
            }
        };
    }
}
