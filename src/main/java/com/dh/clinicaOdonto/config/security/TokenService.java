package com.dh.clinicaOdonto.config.security;

import com.dh.clinicaOdonto.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${clinicaOdonto.jwt.expiration}")
    private String expiration;

    @Value ("${clinicaOdonto.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        Date dataHoje = new Date();
        Date dataExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));

        String token = Jwts.builder()
                .setIssuer("Api ClinicaOdonto")
                .setSubject(usuarioLogado.getUsername())
                .setIssuedAt(dataHoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

        return token;
    }
}
