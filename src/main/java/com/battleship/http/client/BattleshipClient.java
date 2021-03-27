package com.battleship.http.client;

import com.battleship.http.model.AuthenticationReqBody;
import com.battleship.http.model.AuthenticationResBody;
import com.battleship.http.model.BattleshipReqBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "battleshipClient",
        url = "https://battleships.cc/api")
public interface BattleshipClient {
    String AUTH_TOKEN = "Authorization";


    @PostMapping(value = "/authenticate",
            headers = {"Content-Type: application/json"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthenticationResBody authenticate(AuthenticationReqBody request);

    @PostMapping(value = "/tournaments/{tournamentId}/teams",
            headers = {"Content-Type: application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createTeam(@RequestHeader(AUTH_TOKEN) String bearerToken,
                              @PathVariable("tournamentId") String tournamentId);

    @PostMapping(value = "/tournaments/{tournamentId}/battleships",
            headers = {"Content-Type: application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity placeBattleship(@RequestHeader(AUTH_TOKEN) String bearerToken,
                                   @PathVariable("tournamentId") String tournamentId,
                                   BattleshipReqBody requestBody);


}
