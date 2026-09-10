package com.portifolio.marketAPI.DTOs.auth;

import com.portifolio.marketAPI.DTOs.establishment.EstablishmentCreate;
import com.portifolio.marketAPI.DTOs.user.UserCreate;

public record EstablishmentWithOwnerCreate(
        EstablishmentCreate establishment,
        UserCreate owner
) {
}
