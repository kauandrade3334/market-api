package com.portifolio.marketAPI.DTOs.establishment;

import jakarta.validation.constraints.NotBlank;

public record EstablishmentCreate(
        @NotBlank(message = "cnpj do estabalecimento deve ser informado")
        String cnpj,

        @NotBlank(message = "nome do estabelecimento deve ser informado")
        String name,

        @NotBlank(message = "senha de acesso para o estabalecimento deve ser informada")
        String password1,

        @NotBlank(message = "senha de acesso para o estabalecimento deve ser informada")
        String password2
) {
}
