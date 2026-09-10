package com.portifolio.marketAPI.DTOs.user;

import com.portifolio.marketAPI.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserCreate(
        @NotBlank(message = "nome do colaborador deve ser informado")
        String name,

        @NotBlank(message = "senha de acesso para o colaborador deve ser informada")
        String password1,

        @NotBlank(message = "senha de acesso para o colaborador deve ser informada")
        String password2,

        UserRole userRole
) {
}
