package Makers2Front.Application.config;


import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;



@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Config {

    @Bean
    MultipartConfigElement multipartConfig() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("1GB"));
        factory.setMaxRequestSize(DataSize.parse("1GB"));
        return factory.createMultipartConfig();
    }
}
