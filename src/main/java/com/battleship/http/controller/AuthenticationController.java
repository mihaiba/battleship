package com.battleship.http.controller;

import com.battleship.http.client.BattleshipClient;
import com.battleship.http.model.AuthResponseWrapper;
import com.battleship.http.model.AuthenticationReqBody;
import com.battleship.http.model.AuthenticationResBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    private final BattleshipClient client;
    private static final Map<String, AuthResponseWrapper> responseCache = new ConcurrentHashMap<>();

    @PostMapping(value = "/authenticate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResBody authenticate(@RequestBody AuthenticationReqBody request) {
        log.info("request {}", request);
        AuthenticationResBody response = client.authenticate(request);
        responseCache.put(request.getUsername(), AuthResponseWrapper.builder()
                .current(Instant.now())
                .response(response)
                .build());
        log.info("response {}", response);
        return response;
    }

    private String getJWT() {
        if (!responseCache.containsKey("jarvias")) {
            authenticate(AuthenticationReqBody.builder()
                    .username(username)
                    .password(password)
                    .build());
        }
        AuthResponseWrapper jarvias = responseCache.get("jarvias");

        Instant expired = Instant.now().minus(6, ChronoUnit.HOURS);
        if (jarvias.getCurrent().isBefore(expired)) {
            authenticate(AuthenticationReqBody.builder()
                    .username("jarvias")
                    .password("rND4us8yNtVq2B2.EQY-aR")
                    .build());
        }
        return responseCache.get("jarvias").getResponse().getToken();
    }

    @GetMapping(value = "/register/{tournamentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerTeam(@PathVariable("tournamentId") String tournamentId) {
        String jwt = getJWT();
        log.info("registration of team with token {} and tournamentId {}", jwt, tournamentId);
        ResponseEntity response = client.registerTeam(jwt, tournamentId);
        log.info("response {}", response);
        return response;
    }
}
