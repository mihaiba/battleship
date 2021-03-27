package com.battleship.http.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BattleshipClientInterceptor implements RequestInterceptor {

//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String TOKEN_TYPE = "Bearer";

    public void apply(RequestTemplate requestTemplate) {
        log.info("interceptor {}", requestTemplate);
    }

//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
//            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, details.getTokenValue()));
//        }
//    }


}
