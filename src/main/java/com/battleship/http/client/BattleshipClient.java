package com.battleship.http.client;

import com.battleship.http.configuration.FeignConfiguration;
import com.battleship.http.model.AuthenticationReqBody;
import com.battleship.http.model.AuthenticationResBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "battleshipClient",
        url = "http://battleships.cc/api",
        configuration = FeignConfiguration.class)
public interface BattleshipClient {
    String AUTH_TOKEN = "Authorization";


//    @RequestLine("POST /authenticate")

    @RequestMapping(value = "/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    AuthenticationResBody authenticate(@RequestBody AuthenticationReqBody request);

    @RequestMapping(value = "/tournaments/{tournamentId}/teams",
            headers = {"Content-Type: application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
//    @RequestLine("POST /tournaments/{tournamentId}/teams")
    ResponseEntity registerTeam(@RequestHeader(AUTH_TOKEN) String bearerToken,
                                @PathVariable("tournamentId") String tournamentId);
//
//    @PostMapping(value = "/tournaments/{tournamentId}/battleships",
//            headers = {"Content-Type: application/json"},
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity placeBattleship(@RequestHeader(AUTH_TOKEN) String bearerToken,
//                                   @PathVariable("tournamentId") String tournamentId,
//                                   BattleshipReqBody requestBody);


}
