package com.wfld.claims.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HeartbeatController {

    @GetMapping("/heartbeat")
    public Mono<String> heartbeat() {
        return Mono.just("OK");
    }
} 