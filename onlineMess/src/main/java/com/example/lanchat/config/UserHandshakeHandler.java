package com.example.lanchat.config;

import java.security.Principal;
import java.util.UUID;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(@NonNull ServerHttpRequest request, @NonNull WebSocketHandler wsHandler,
                                      @NonNull java.util.Map<String, Object> attributes) {
        String username = null;
        if (request instanceof ServletServerHttpRequest servletRequest) {
            MultiValueMap<String, String> params = UriComponentsBuilder.fromUri(servletRequest.getURI()).build().getQueryParams();
            username = params.getFirst("username");
        }

        if (username == null || username.isBlank()) {
            username = "Guest-" + UUID.randomUUID();
        }
        return new StompPrincipal(username);
    }

    private static final class StompPrincipal implements Principal {
        private final String name;

        private StompPrincipal(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
