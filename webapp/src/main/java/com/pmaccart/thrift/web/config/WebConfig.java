package com.pmaccart.thrift.web.config;

import com.pmaccart.thrift.handler.WeatherHandler;
import com.pmaccart.thrift.model.WeatherService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.pmaccart.thrift.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WeatherService.Processor processor() {
        WeatherHandler handler = new WeatherHandler();
        return new WeatherService.Processor(handler);
    }

    @Bean
    public TBinaryProtocol.Factory inProtocolFactory() {
        return new TBinaryProtocol.Factory(true, true);
    }

    @Bean
    public TBinaryProtocol.Factory outProtocolFactory() {
        return new TBinaryProtocol.Factory(true, true);
    }
}
