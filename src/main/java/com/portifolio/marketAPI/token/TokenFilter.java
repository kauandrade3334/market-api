package com.portifolio.marketAPI.token;

import com.portifolio.marketAPI.exception.UnauthorizedException;
import com.portifolio.marketAPI.userPrincipal.UserPrincipal;
import com.portifolio.marketAPI.userPrincipal.UserPrincipalService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserPrincipalService userPrincipalService;

    public TokenFilter(TokenService tokenService, UserPrincipalService userPrincipalService) {
        this.tokenService = tokenService;
        this.userPrincipalService = userPrincipalService;
    }


    // cria o userProfile com base no token
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // se não existe token: deixa o Spring Security verificar a rota e liberar se for pública
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String id = tokenService.extractUserId(token);

            if (id != null
                    && tokenService.isTokenValid(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserPrincipal userPrincipal =
                        userPrincipalService.loadUserByUsername(id);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userPrincipal,
                                null,
                                userPrincipal.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {
            throw new UnauthorizedException(
                    "Erro ao validar o token de acesso: " + e.getMessage()
            );
        }

        filterChain.doFilter(request, response);
    }
}
