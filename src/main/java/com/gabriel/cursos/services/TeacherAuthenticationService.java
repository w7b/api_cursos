package com.gabriel.cursos.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gabriel.cursos.dto.ProfessorRecord;
import com.gabriel.cursos.dto.Token;
import com.gabriel.cursos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class TeacherAuthenticationService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private ProfessorRepository repository;

    public Token execute(ProfessorRecord professorRecord) {
        var professorExists = repository.findByEmail(professorRecord.email()).orElseThrow(() ->
                new UsernameNotFoundException("Username or Password incorrect."));

        var roles = Arrays.asList("PROFESSOR");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(professorExists.getId().toString())
                .withClaim("roles", roles)
                .sign(algorithm);

        return new Token(token, expiresIn, roles);
    }

}
