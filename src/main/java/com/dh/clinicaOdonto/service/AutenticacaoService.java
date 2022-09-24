package com.dh.clinicaOdonto.service;

import com.dh.clinicaOdonto.config.security.TokenService;
import com.dh.clinicaOdonto.entity.Usuario;
import com.dh.clinicaOdonto.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    Logger logger  = Logger.getLogger(TokenService.class);

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            logger.info("Usuário encontrado");

            Usuario usuario =  repository.findByUsername(username);
            return usuario;
        }catch (UsernameNotFoundException exception){
            logger.info("Usuário não encontrado");

            throw new UsernameNotFoundException("Usuario não existe");
        }
    }
}
