package com.dh.clinicaOdonto;

import com.dh.clinicaOdonto.entity.Role;
import com.dh.clinicaOdonto.entity.Usuario;
import com.dh.clinicaOdonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserRun implements ApplicationRunner {


    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Role perfilUser = new Role();
        Role perfilAdmin = new Role();
        perfilUser.setDescricao("USER");
        perfilAdmin.setDescricao("ADMIN");

        List<Role> perfilAdmin1 = new ArrayList<>();
        List<Role> perfilUser1 = new ArrayList<>();

        perfilAdmin1.add(perfilAdmin);
        perfilUser1.add(perfilUser);

        Usuario admin1 = new Usuario();
        Usuario user1 = new Usuario();

        admin1.setUsername("@admin");
        admin1.setPassword(encoder.encode("@admin"));
        admin1.setRole(perfilAdmin1);

        user1.setUsername("user01");
        user1.setPassword(encoder.encode("user01"));
        user1.setRole(perfilUser1);

        repository.save(admin1);
        repository.save(user1);
    }
}
