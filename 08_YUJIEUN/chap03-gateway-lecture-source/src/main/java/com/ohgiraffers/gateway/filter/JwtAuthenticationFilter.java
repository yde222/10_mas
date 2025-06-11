package com.ohgiraffers.gateway.filter;

import com.ohgiraffers.gateway.jwt.GatewayJwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {


    private final GatewayJwtTokenProvider jwtTokenProvider;

    /***
     * Reactive Gateway에서는 WebFlux 기술이 사용된다.
     * 비동기/논블로킹 특징으로 대규코 어플리케이션에서 성능적인 부분이 좋다.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //헤더에서 'Authorization'값을 읽어온다,
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(authHeader ==null || !authHeader.startsWith("Bearer")){
            return chain.filter(exchange);
        }
        // "Bearer" 접두어를 제거하구 순수 JWT 토큰만 추출한다
        String token = authHeader.substring(7);

        if(!jwtTokenProvider.validateToken(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        Long userId = jwtTokenProvider.getUserIdFormJWT(token);
        String role = jwtTokenProvider.getRoleFormJWT(token);

        ServerHttpRequest mutateRequest = exchange.getRequest().mutate()
                .header("X-User-Id", String.valueof(userId))
                .header("X-User-Role", role)
                .build();

        return chain.filter(mutatedExchange);
    }

    /***
     * Global
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
