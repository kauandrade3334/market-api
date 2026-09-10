package com.portifolio.marketAPI.DTOs.user;

import jakarta.validation.constraints.NotBlank;

public record UserCreate(
        @NotBlank(message = "nome do colaborador deve ser informado")
        String name,

        @NotBlank(message = "senha de acesso para o colaborador deve ser informada")
        String password1,

        @NotBlank(message = "senha de acesso para o colaborador deve ser informada")
        String password2
) {
}
