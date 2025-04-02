package com.wfld.claims.demo.heartbeat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.wfld.claims.demo.controllers.HeartbeatController;

public class HeartbeatControllerTests {

    @Test
    void testHeartbeatRespondsWithOK() {
        WebTestClient client;
        client = WebTestClient.bindToController(new HeartbeatController()).build();
        client.get().uri("/heartbeat").exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("OK");
    }
    
}
