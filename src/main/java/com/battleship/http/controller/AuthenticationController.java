package com.battleship.http.controller;

import com.battleship.http.client.BattleshipClient;
import com.battleship.http.model.AuthenticationReqBody;
import com.battleship.http.model.AuthenticationResBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final BattleshipClient client;

    @PostMapping(value = "/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResBody authenticate(AuthenticationReqBody request) {
        log.info("request {}", request);
        AuthenticationResBody response = client.authenticate(request);
        log.info("response {}", response);
        return response;
    }
}
