package com.gabriel.cursos.dto;

import java.time.Instant;
import java.util.List;

public record Token(String token, Instant expiresIn, List<String> roles) {
}
