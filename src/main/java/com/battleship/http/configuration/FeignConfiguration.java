package com.battleship.http.configuration;

import feign.Contract;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
