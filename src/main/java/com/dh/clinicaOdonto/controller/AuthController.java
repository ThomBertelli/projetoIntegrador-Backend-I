package com.dh.clinicaOdonto.controller;

import com.dh.clinicaOdonto.config.security.TokenService;
import com.dh.clinicaOdonto.dto.TokenDTO;
import com.dh.clinicaOdonto.dto.UsuarioDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    Logger logger  = Logger.getLogger(TokenService.class);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
        public ResponseEntity autenticar (@RequestBody @Valid UsuarioDTO usuarioDTO) {

        try {
            logger.info("Login autenticado");
            UsernamePasswordAuthenticationToken loginUsuario = usuarioDTO.converter();

            Authentication authentication = authManager.authenticate(loginUsuario);

            String token = tokenService.gerarToken(authentication);

            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setTipo("Bearer");

            return new ResponseEntity(tokenDTO, HttpStatus.OK);
        } catch (AuthenticationException exception) {
            logger.info("Falha ao autenticar o login");
            return new ResponseEntity("Erro ao autenticar", HttpStatus.BAD_REQUEST);
        }
    }
}
