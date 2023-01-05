package br.com.jneris.springsecurity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/public")
    public String publicRoute(){
        return "<h1> Public Route, feel free to lock around! </h1>";
    }
    @GetMapping("/private")
    public String privateRoute(@AuthenticationPrincipal OidcUser principal){
        return String.format("""
                        <h1>Private Route, only authorized personal! </h1>
                        """);
    }
    @GetMapping("/cookie")
    public String privateRouteCookie (@AuthenticationPrincipal OidcUser principal){
        return String.format("""
                        <h1>Private Route, only authorized personal! </h1>
                        <h3>Principal: %s</h3>
                        <h3>Name: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s</h3>
                        """, principal, principal.getAttribute("name"), principal.getAttribute("email"), principal.getAuthorities(), principal.getIdToken().getTokenValue());
    }

    @GetMapping("/jwt")
    public String jwt(@AuthenticationPrincipal Jwt jwt){
        return String.format("""
                        Principal: %s\n
                        Email: %s\n
                        JWT: %s\n
                        """, jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
    }
}
