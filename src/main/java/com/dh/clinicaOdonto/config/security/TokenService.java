package com.dh.clinicaOdonto.config.security;

import com.dh.clinicaOdonto.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    Logger logger  = Logger.getLogger(TokenService.class);
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

    public boolean verificaToken(String token) {
        try {
            logger.info("Token verificado");
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        }catch (Exception exception){
            logger.info("Token não verificado");
        return false;
        }
    }

    public String getUsernameUsuario(String token) {
        Claims clains = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        String username = clains.getSubject();

        return username;
    }
}
